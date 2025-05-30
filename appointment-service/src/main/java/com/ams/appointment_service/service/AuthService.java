package com.ams.appointment_service.service;

import com.ams.appointment_service.multitenancy.schema.sevice.TenantSchemaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final TenantSchemaService tenantService;

    public boolean createTenant(String tenantId) {
        try {
            tenantService.createTenantSchema(tenantId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteTenant(String tenantId) {
        try {
            tenantService.dropTenantSchema(tenantId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
