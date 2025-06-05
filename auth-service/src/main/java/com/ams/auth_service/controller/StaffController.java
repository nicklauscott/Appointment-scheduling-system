package com.ams.auth_service.controller;

import com.ams.auth_service.dto.*;
import com.ams.auth_service.service.StaffService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff")
@Tag(name = "Staff", description = "Api for staffs login")
@AllArgsConstructor
public class StaffController {

    private StaffService service;

    @PostMapping("/login")
    @Operation(summary = "Login a staff")
    public ResponseEntity<TokenPairResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO request
    ) {
        return ResponseEntity.ok(service.login(request));
    }

    @PostMapping("/refresh")
    @Operation(summary = "Refresh staff access token")
    public ResponseEntity<TokenPairResponseDTO> refresh(
            @Valid @RequestBody RefreshTokenRequestDTO request
    ) {
        return ResponseEntity.ok(service.refresh(request));
    }
}
