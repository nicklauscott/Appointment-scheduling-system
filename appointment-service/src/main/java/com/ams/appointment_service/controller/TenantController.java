package com.ams.appointment_service.controller;

import com.ams.appointment_service.multitenancy.database.database_resolver.TenantContext;
import com.ams.appointment_service.multitenancy.schema.sevice.TenantSchemaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tenant")
@AllArgsConstructor
public class TenantController {

    private final TenantSchemaService schemaService;

    @GetMapping("/{tenant}")
    public String createTenant(@PathVariable("tenant") String tenantId) {
        schemaService.createTenantSchema(tenantId);
        return TenantContext.INSTANCE.getCurrentTenant();
    }

}
