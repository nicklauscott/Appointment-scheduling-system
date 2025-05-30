package com.ams.appointment_service.multitenancy.schema.sevice;

import com.ams.appointment_service.multitenancy.schema.schema_resolver.SchemaInitialization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TenantSchemaService {

    private static final Logger log = LoggerFactory.getLogger(TenantSchemaService.class);

    private final JdbcTemplate jdbcTemplate;
    private final SchemaInitialization schemaInitialization;

    public TenantSchemaService(JdbcTemplate jdbcTemplate, SchemaInitialization schemaInitialization) {
        this.jdbcTemplate = jdbcTemplate;
        this.schemaInitialization = schemaInitialization;
    }

    public void dropTenantSchema(String tenantId) {
        try {
            jdbcTemplate.execute("DROP SCHEMA IF EXISTS " + tenantId);
            log.info("Dropping {} schema...", tenantId);
        } catch (Exception e) {
            log.error("Couldn't Drop {} schema \ne -> ", tenantId, e);
            throw new RuntimeException(e);
        }
    }

    public void createTenantSchema(String tenantId) {
        try {
            jdbcTemplate.execute("CREATE SCHEMA IF NOT EXISTS " + tenantId);
            log.info("Creating {} schema...", tenantId);
        } catch (Exception e) {
            log.error("Couldn't create {} schema \ne -> ", tenantId, e);
            throw new RuntimeException(e);
        }

        try {
            if (schemaAlreadyInitialized(tenantId)) return;
            log.info("Initializing {} schema...", tenantId);
            schemaInitialization.initializeSchema(tenantId);
            log.info("Initializing {} schema completed.", tenantId);
        } catch (Exception e) {
            log.error("Couldn't initialize {} schema \ne -> ", tenantId, e);
            throw new RuntimeException(e);
        }
    }

    private boolean schemaAlreadyInitialized(String schema) {
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(
                "SELECT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = ? AND table_name = 'flyway_schema_history')",
                Boolean.class,
                schema
        ));
    }

}



