package com.ams.tenant_service.model.dto.tenant;

import lombok.Data;
import java.util.Collections;
import java.util.Map;

@Data
public class TenantDTO {
    private String tenantId;
    private String companyName;
    private String logoUrl;
    private String primaryColor;
    private String domain;
    private ContactInfo contactInfo;
    private NotificationPreferences notificationPreferences;
    private Map<String, String> customConfig = Collections.emptyMap();
}