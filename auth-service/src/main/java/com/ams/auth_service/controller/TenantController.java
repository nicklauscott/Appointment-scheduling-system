package com.ams.auth_service.controller;

import com.ams.auth_service.dto.*;
import com.ams.auth_service.service.TenantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/tenant")
@AllArgsConstructor
@Tag(name = "Tenant", description = "Api for managing tenants")
public class TenantController {

    private TenantService service;

    @PostMapping("/login")
    @Operation(summary = "Login tenant")
    public ResponseEntity<TokenPairResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO request
    ) throws NoSuchAlgorithmException {
        return ResponseEntity.ok(service.login(request));
    }

    @PostMapping("/refresh")
    @Operation(summary = "Refresh tenant access token")
    public ResponseEntity<TokenPairResponseDTO> refresh(
            @Valid @RequestBody RefreshTokenRequestDTO request
    ) throws NoSuchAlgorithmException {
        return ResponseEntity.ok(service.refresh(request));
    }

    @PostMapping("/register")
    @Operation(summary = "Register a tenant")
    public ResponseEntity<RegisterTenantResponseDTO> register(
            @Valid @RequestBody RegisterTenantRequestDTO request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/register_staff")
    @Operation(summary = "Register a staff")
    public ResponseEntity<RegisterStaffResponseDTO> register(
            @Valid @RequestBody RegisterStaffRequestDTO request
    ) {
        return ResponseEntity.ok(service.registerStaff(request));
    }

}
