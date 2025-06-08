package com.ams.auth_service.service;

import com.ams.auth_service.dto.ValidateResponseDTO;
import com.ams.auth_service.jwt.JwtService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class TokenValidationService {

    private JwtService jwtService;

    public ValidateResponseDTO validateToken(String token) {
        Pair<String, String> emailAndTenantId = jwtService.getEmailAndTenantIdFromToken(token);
        String role = jwtService.getRoleFromToken(token);

        return new ValidateResponseDTO(emailAndTenantId.getRight(), emailAndTenantId.getLeft(), role);
    }

}
