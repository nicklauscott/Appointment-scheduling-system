package com.ams.appointment_service.multitenancy.database.database_resolver;

import com.ams.appointment_service.multitenancy.schema.schema_resolver.TenantContext;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

//@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {

    @Override
    @NonNull
    public String resolveCurrentTenantIdentifier() {
        var tenant = TenantContext.INSTANCE.getCurrentTenant();
        String DEFAULT_TENANT = "test_db";
        if (tenant != null)  return tenant;
        else return DEFAULT_TENANT;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}

