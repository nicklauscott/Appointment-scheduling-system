package com.ams.tenant_service.service;

import com.ams.tenant_service.exception.TenantNotFoundException;
import com.ams.tenant_service.model.entities.NotificationPreferences;
import com.ams.tenant_service.model.entities.Tenant;
import com.ams.tenant_service.repository.TenantRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class TenantService {

    private final TenantRepository repository;


    public Tenant getTenantById(String tenantId) {
        return repository.findById(tenantId)
                .orElseThrow(() -> new TenantNotFoundException("Tenant with the id doesn't exist: " + tenantId));
    }

    @Transactional
    public void updateTenant() {
        Tenant tenant = getTenantById("abc");
        NotificationPreferences notification = new NotificationPreferences();
        Map<String, String> data = new HashMap<>();
        data.put("layout", "");
        data.put("APPOINTMENT_BOOKED", "");
        notification.setTemplates(data);
        tenant.setNotificationPreferences(notification);
        repository.save(tenant);
    }

}
