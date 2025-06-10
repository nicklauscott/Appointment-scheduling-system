package com.ams.auth_service.jwt;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@Data
public class TenantContext {

    private String tenantId;

}


