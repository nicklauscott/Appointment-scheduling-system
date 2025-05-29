package com.ams.appointment_service.multitenancy.schema.schema_resolver;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class SchemaTenantIdentifierResolver implements CurrentTenantIdentifierResolver {

    @Override
    @NonNull
    public String resolveCurrentTenantIdentifier() {
        var tenant = TenantContext.INSTANCE.getCurrentTenant();
        String DEFAULT_TENANT = "public";
        if (tenant != null)  return tenant;
        else return DEFAULT_TENANT;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}

