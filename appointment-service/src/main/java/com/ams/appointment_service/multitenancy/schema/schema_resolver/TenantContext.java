package com.ams.appointment_service.multitenancy.schema.schema_resolver;

public enum TenantContext {
    INSTANCE;

    private final ThreadLocal<String> currentTenant = new ThreadLocal<>();

    public void setCurrentTenant(String tenant) {
        currentTenant.set(tenant);
    }

    public String getCurrentTenant() {
        return currentTenant.get();
    }

    public void clear() {
        currentTenant.remove();
    }
}

