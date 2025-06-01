package com.ams.tenant_service.multitenancy.schema.schema_resolver;

import java.util.Map;

public enum TenantContext {
    INSTANCE;

    private final ThreadLocal<String> currentTenant = new ThreadLocal<>();

    private final ThreadLocal<Map<String, String>> requestDetail = new ThreadLocal<>();

    public void setCurrentTenant(String tenant) {
        currentTenant.set(tenant);
    }

    public String getCurrentTenant() {
        return currentTenant.get();
    }

    public void setRequestDetail(Map<String, String> requestDetails) {
        requestDetail.set(requestDetails);
    }

    public Map<String, String> getRequestDetail() {
        return requestDetail.get();
    }

    public void clear() {
        currentTenant.remove();
        requestDetail.remove();
    }
}

