package com.ams.auth_service.controller;

import com.ams.auth_service.dto.ValidateResponseDTO;
import com.ams.auth_service.service.TokenValidationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validate")
@Tag(name = "Validate", description = "Api for validating tokens")
@AllArgsConstructor
public class TokenValidationController {

    private final TokenValidationService service;

    @GetMapping
    @Operation(summary = "Validate token")
    public ResponseEntity<ValidateResponseDTO> validateToken(
            @RequestHeader("Authorization") String authHeader
    ) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(service.validateToken(authHeader.substring(7)));
    }

}
