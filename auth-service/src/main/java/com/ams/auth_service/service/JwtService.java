package com.ams.auth_service.service;

import com.ams.auth_service.dto.TokenPairResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class JwtService {


    public TokenPairResponseDTO generateTokenPair(String tenantId, String email, String role) {
        String accessToken = generateAccessToken(tenantId, email, role);
        String refreshToken = generateRefreshToken(tenantId, email);
        return new TokenPairResponseDTO(accessToken, refreshToken);
    }

    public String generateAccessToken(String tenantId, String email, String role) {
        return "access";
    }

    public String generateRefreshToken(String tenantId, String email) {
        return "refresh";
    }

}
