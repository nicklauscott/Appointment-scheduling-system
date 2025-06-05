package com.ams.auth_service.service;

import com.ams.auth_service.dto.*;
import com.ams.auth_service.exception.BadCredentialsException;
import com.ams.auth_service.exception.InvalidOrExpiredTokenException;
import com.ams.auth_service.exception.StaffAlreadyExistException;
import com.ams.auth_service.exception.TenantAlreadyExistException;
import com.ams.auth_service.jwt.JwtService;
import com.ams.auth_service.model.RefreshToken;
import com.ams.auth_service.model.Staff;
import com.ams.auth_service.model.Tenant;
import com.ams.auth_service.repository.RefreshTokenRepository;
import com.ams.auth_service.repository.StaffRepository;
import com.ams.auth_service.repository.TenantRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;

@Service
@AllArgsConstructor
public class TenantService {

    private TenantRepository repository;
    private RefreshTokenRepository tokenRepository;

    private StaffRepository staffRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;

    @Transactional
    public TokenPairResponseDTO login(LoginRequestDTO request) throws NoSuchAlgorithmException {
        Tenant tenant = repository.findByIdAndEmail(request.getTenantId(), request.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Invalid credentials! check your tenant id, email and password"));

        if (!passwordEncoder.matches(request.getPassword(), tenant.getHashedPassword())) {
            throw new BadCredentialsException("Invalid credentials! check your tenant id, email and password");
        }

        tokenRepository.deleteByEmailAndTenantId(request.getEmail(), request.getTenantId());
        tokenRepository.flush(); // Force Hibernate to flush the delete to DB

        TokenPairResponseDTO tokens = jwtService.generateTokenPair(tenant.getId(), tenant.getEmail(), "ADMIN");

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setEmail(request.getEmail());
        refreshToken.setTenantId(request.getTenantId());
        refreshToken.setHashedToken(hashToken(tokens.getRefresh()));
        tokenRepository.save(refreshToken);

        return tokens;
    }

    @Transactional
    public TokenPairResponseDTO refresh(RefreshTokenRequestDTO request) throws NoSuchAlgorithmException {
        Pair<String, String> claims = jwtService.getEmailAndTenantIdFromToken(request.getRefresh());

        repository.findByIdAndEmail(claims.getRight(), claims.getLeft())
                .orElseThrow(() -> new InvalidOrExpiredTokenException("Invalid or expired token"));

        RefreshToken token = tokenRepository.findByEmailAndTenantId(claims.getLeft(), claims.getRight())
                .orElseThrow(() -> new InvalidOrExpiredTokenException("Invalid or expired token"));

        if (!Objects.equals(hashToken(request.getRefresh()), token.getHashedToken())) {
            throw new InvalidOrExpiredTokenException("Invalid or expired token");
        }

        tokenRepository.delete(token);
        tokenRepository.flush();

        TokenPairResponseDTO tokens = jwtService
                .generateTokenPair(claims.getRight(), claims.getLeft(), "ADMIN");

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setEmail(claims.getLeft());
        refreshToken.setTenantId(claims.getRight());
        refreshToken.setHashedToken(hashToken(tokens.getRefresh()));
        tokenRepository.save(refreshToken);

        return tokens;
    }

    public RegisterTenantResponseDTO register(RegisterTenantRequestDTO request) {
        if (repository.existsById(request.getTenantId())) {
            throw new TenantAlreadyExistException("Tenant with the id " + request.getTenantId() + " already exist");
        }

        Tenant tenant = new Tenant();
        tenant.setId(request.getTenantId());
        tenant.setName(request.getTenantName());
        tenant.setEmail(request.getTenantEmail());
        tenant.setHashedPassword(passwordEncoder.encode(request.getPassword()));

        Tenant dbTenant = repository.save(tenant);
        return new RegisterTenantResponseDTO(dbTenant.getId(), dbTenant.getName(), dbTenant.getEmail());
    }

    public RegisterStaffResponseDTO registerStaff(RegisterStaffRequestDTO request) {
        if (staffRepository.findByEmailAndTenantId(request.getEmail(), request.getTenantId()).isPresent()) {
            throw new StaffAlreadyExistException("Staff with the email " + request.getEmail()
                    + " already exist for tenant " + request.getTenantId());
        }

        Staff staff = new Staff();
        staff.setEmail(request.getEmail());
        staff.setHashedPassword(passwordEncoder.encode(request.getPassword()));
        if (request.getRole() != null) staff.setRole(request.getRole());
        staff.setTenantId(request.getTenantId());

        Staff dbStaff = staffRepository.save(staff);
        return new RegisterStaffResponseDTO(dbStaff.getTenantId(), dbStaff.getEmail());
    }

    private String hashToken(String token) throws NoSuchAlgorithmException {
        var digest = MessageDigest.getInstance("SHA-256");
        var hashBytes = digest.digest(token.getBytes());
        return Base64.getEncoder().encodeToString(hashBytes);
    }

}
