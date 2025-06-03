package com.ams.tenant_service.model.dto.tenant;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Collections;
import java.util.Map;

@Data
@AllArgsConstructor
public class NotificationPreferences {
    private String senderEmail;
    private Map<String, String> templates;
}
