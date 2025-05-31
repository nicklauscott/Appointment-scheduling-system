package com.ams.tenant_service.service;

import com.ams.tenant_service.exception.TenantNotFoundException;
import com.ams.tenant_service.model.entities.Tenant;
import com.ams.tenant_service.repository.TenantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TenantService {

    private final TenantRepository repository;

    public Tenant getTenantById(String tenantId) {
        return repository.findById(tenantId)
                .orElseThrow(() -> new TenantNotFoundException("Tenant with the id doesn't exist: " + tenantId));
    }



}
