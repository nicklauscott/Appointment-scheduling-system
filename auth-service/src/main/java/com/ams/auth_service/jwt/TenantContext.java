package com.ams.auth_service.jwt;

import java.util.Map;

public enum TenantContext {
    INSTANCE;

    private final ThreadLocal<Map<String, String>> requestDetail = new ThreadLocal<>();

    public void setRequestDetail(Map<String, String> requestDetails) {
        requestDetail.set(requestDetails);
    }

    public Map<String, String> getRequestDetail() {
        return requestDetail.get();
    }

    public void clear() { requestDetail.remove(); }
}

