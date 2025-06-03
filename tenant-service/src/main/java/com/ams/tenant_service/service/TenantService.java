package com.ams.tenant_service.service;

import com.ams.tenant_service.exception.TenantNotFoundException;
import com.ams.tenant_service.model.dto.tenant.TenantDTO;
import com.ams.tenant_service.model.entities.Tenant;
import com.ams.tenant_service.model.mapper.TenantDTOMapper;
import com.ams.tenant_service.multitenancy.schema.schema_resolver.TenantContext;
import com.ams.tenant_service.repository.TenantRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TenantService {

    private final TenantRepository repository;

    public Tenant getTenantById(String tenantId) {
        return repository.findById(tenantId)
                .orElseThrow(() -> new TenantNotFoundException("Tenant with the id " + tenantId + " doesn't exist"));
    }

    public Tenant createTenant(String tenantId) {
        Optional<Tenant> optional = repository.findById(tenantId);
        if (optional.isPresent()) return null;
        Tenant tenant = new Tenant();
        tenant.setTenantId(tenantId);
        return repository.save(tenant);
    }

    public void deleteTenant(String tenantId) {
        repository.deleteById(tenantId);
    }

    @Transactional
    public TenantDTO getTenant() {
        String tenantId = TenantContext.INSTANCE.getRequestDetail().get("X-Tenant-ID");
        Tenant tenant = repository.findById(tenantId)
                .orElseThrow(() -> new TenantNotFoundException("Tenant with the id " + tenantId + " doesn't exist"));
        return TenantDTOMapper.toDTO(tenant);
    }

    @Transactional
    public TenantDTO updateTenant(TenantDTO request) {
        String tenantId = TenantContext.INSTANCE.getRequestDetail().get("X-Tenant-ID");
        repository.findById(tenantId)
                .orElseThrow(() -> new TenantNotFoundException("Tenant with the id " + tenantId + " doesn't exist"));
        Tenant tenant = TenantDTOMapper.toEntity(request);
        Tenant updatedTenant = repository.save(tenant);
        return TenantDTOMapper.toDTO(updatedTenant);
    }

}
