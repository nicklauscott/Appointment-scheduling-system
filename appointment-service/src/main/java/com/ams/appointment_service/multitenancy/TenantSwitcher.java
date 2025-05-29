package com.ams.appointment_service.multitenancy;

import com.ams.appointment_service.multitenancy.schema_resolver.TenantContext;

public class TenantSwitcher {

    public void switchTenant(String tenantId) {
        TenantContext.INSTANCE.setCurrentTenant(tenantId);
    }

    public void clearTenant(String tenantId) {
        TenantContext.INSTANCE.clear();
    }

}
