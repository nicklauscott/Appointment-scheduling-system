package com.ams.auth_service.service;

import com.ams.auth_service.dto.LoginRequestDTO;
import com.ams.auth_service.dto.RefreshTokenRequestDTO;
import com.ams.auth_service.dto.TokenPairResponseDTO;
import com.ams.auth_service.exception.BadCredentialsException;
import com.ams.auth_service.exception.InvalidOrExpiredTokenException;
import com.ams.auth_service.jwt.JwtService;
import com.ams.auth_service.model.RefreshToken;
import com.ams.auth_service.model.Staff;
import com.ams.auth_service.repository.RefreshTokenRepository;
import com.ams.auth_service.repository.StaffRepository;
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
public class StaffService {

    private StaffRepository repository;
    private RefreshTokenRepository tokenRepository;

    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;

    @Transactional
    public TokenPairResponseDTO login(LoginRequestDTO request) throws NoSuchAlgorithmException {
        Staff staff = repository.findByEmailAndTenantId(request.getEmail(), request.getTenantId())
                .orElseThrow(() -> new BadCredentialsException("Invalid credentials! check your tenant id, email and password 1"));

        if (!passwordEncoder.matches(request.getPassword(), staff.getHashedPassword())) {
            throw new BadCredentialsException("Invalid credentials! check your tenant id, email and password 2");
        }

        tokenRepository.deleteByEmailAndTenantId(request.getEmail(), request.getTenantId());
        tokenRepository.flush();

        TokenPairResponseDTO tokens = jwtService.generateTokenPair(request.getTenantId(), request.getEmail(), "USER");

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
        String role = jwtService.getRoleFromToken(request.getRefresh());

        repository.findByEmailAndTenantId(claims.getRight(), claims.getLeft())
                .orElseThrow(() -> new InvalidOrExpiredTokenException("Invalid or expired token"));

        RefreshToken token = tokenRepository.findByEmailAndTenantId(claims.getLeft(), claims.getRight())
                .orElseThrow(() -> new InvalidOrExpiredTokenException("Invalid or expired token"));

        if (!Objects.equals(hashToken(request.getRefresh()), token.getHashedToken())) {
            throw new InvalidOrExpiredTokenException("Invalid or expired token");
        }

        tokenRepository.delete(token);
        tokenRepository.flush();

        TokenPairResponseDTO tokens = jwtService
                .generateTokenPair(claims.getRight(), claims.getLeft(), role);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setEmail(claims.getLeft());
        refreshToken.setTenantId(claims.getRight());
        refreshToken.setHashedToken(hashToken(tokens.getRefresh()));
        tokenRepository.save(refreshToken);

        return tokens;
    }

    private String hashToken(String token) throws NoSuchAlgorithmException {
        var digest = MessageDigest.getInstance("SHA-256");
        var hashBytes = digest.digest(token.getBytes());
        return Base64.getEncoder().encodeToString(hashBytes);
    }

}
