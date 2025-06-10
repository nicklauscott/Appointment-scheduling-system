package com.ams.tenant_service.multitenancy.schema.schema_resolver;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

@Component
@AllArgsConstructor
public class PublicSchemaInitializer {

    private final JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate.execute(
                """ 
                        -- Main Tenant table
                        CREATE TABLE IF NOT EXISTS public.tenants (
                            tenant_id TEXT PRIMARY KEY,
                            company_name TEXT NOT NULL DEFAULT '',
                            logo_url TEXT NOT NULL DEFAULT '',
                            primary_color TEXT NOT NULL DEFAULT '',
                            domain TEXT NOT NULL DEFAULT '',
                        
                            -- Embedded ContactInfo fields
                            email TEXT NOT NULL DEFAULT '',
                            phone TEXT NOT NULL DEFAULT '',
                            address TEXT NOT NULL DEFAULT '',
                        
                            -- Embedded NotificationPreferences field
                            sender_email TEXT NOT NULL DEFAULT ''
                        );
                        
                        -- notification_templates map
                        CREATE TABLE IF NOT EXISTS public.notification_templates (
                            tenant_id TEXT NOT NULL,
                            template_type TEXT NOT NULL,
                            template_content TEXT,
                        
                            PRIMARY KEY (tenant_id, template_type),
                            FOREIGN KEY (tenant_id) REFERENCES public.tenants (tenant_id) ON DELETE CASCADE
                        );
                        
                        -- tenant_custom_config map
                        CREATE TABLE IF NOT EXISTS public.tenant_custom_config (
                            tenant_id TEXT NOT NULL,
                            config_key TEXT NOT NULL,
                            config_value TEXT,
                        
                            PRIMARY KEY (tenant_id, config_key),
                            FOREIGN KEY (tenant_id) REFERENCES public.tenants (tenant_id) ON DELETE CASCADE
                        );
                        
                """);
    }

}
