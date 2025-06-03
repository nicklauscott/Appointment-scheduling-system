package com.ams.tenant_service.controller;

import com.ams.tenant_service.model.dto.tenant.TenantDTO;
import com.ams.tenant_service.service.TenantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/tenant")
@Tag(name = "Tenant", description = "Api for managing tenants")
@AllArgsConstructor
public class TenantController {

    private final TenantService tenantService;

    @GetMapping
    @Operation(summary = "Get tenant detail")
    public ResponseEntity<TenantDTO> getTenant() {
        return ResponseEntity.ok(tenantService.getTenant());
    }

    @PatchMapping
    @Operation(summary = "Update tenant detail")
    public ResponseEntity<TenantDTO> updateTenant(@RequestBody TenantDTO request) {
        return ResponseEntity.ok(tenantService.updateTenant(request));
    }

}
