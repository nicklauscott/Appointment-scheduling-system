package com.ams.appointment_service.multitenancy.schema.schema_resolver;

import lombok.AllArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;

@Component
@AllArgsConstructor
public class SchemaInitialization {

    private final DataSource dataSource;

    public void initializeSchema(String tenantId) {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .schemas(tenantId)
                .locations("classpath:db/migration")
                .load();

        flyway.migrate();
    }



}

