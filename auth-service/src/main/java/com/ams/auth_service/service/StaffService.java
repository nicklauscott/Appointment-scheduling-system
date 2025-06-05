package com.ams.auth_service.service;

import com.ams.auth_service.dto.LoginRequestDTO;
import com.ams.auth_service.dto.RefreshTokenRequestDTO;
import com.ams.auth_service.dto.TokenPairResponseDTO;
import com.ams.auth_service.exception.BadCredentialsException;
import com.ams.auth_service.model.Staff;
import com.ams.auth_service.repository.StaffRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StaffService {

    private PasswordEncoder passwordEncoder;
    private StaffRepository repository;
    private JwtService jwtService;

    public TokenPairResponseDTO login(LoginRequestDTO request) {
        Staff staff = repository.findByEmailAndTenantId(request.getEmail(), request.getTenantId())
                .orElseThrow(() -> new BadCredentialsException("Invalid credentials! check your tenant id, email and password"));

        if (!passwordEncoder.matches(request.getPassword(), staff.getHashedPassword())) {
            throw new BadCredentialsException("Invalid credentials! check your tenant id, email and password");
        }

        return jwtService.generateTokenPair(staff.getTenantId(), staff.getEmail(), staff.getRole());
    }

    public TokenPairResponseDTO refresh(RefreshTokenRequestDTO request) {


        return jwtService.generateTokenPair("", "", "");
    }


}
