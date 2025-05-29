package com.ams.appointment_service.multitenancy.schema.schema_resolver;

import com.ams.appointment_service.exception.TenantNotFoundException;
import lombok.AllArgsConstructor;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import javax.sql.DataSource;
import java.sql.SQLException;

@AllArgsConstructor
public class SchemaMultiTenantConnectionProvider
        extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

    private static final Logger log = LoggerFactory.getLogger(SchemaMultiTenantConnectionProvider.class);

    private final DataSource dataSource;

    @Override
    protected DataSource selectAnyDataSource() {
        return dataSource;
    }

    @Override
    protected DataSource selectDataSource(String schema) {
        try {
            var connection = dataSource.getConnection();
            connection.setSchema(schema);
            return new SingleConnectionDataSource(connection, true);
        } catch (SQLException e) {
            log.info("Failed to switch schema to {}. ", schema, e);
            throw new TenantNotFoundException("Invalid X-Tenant-ID value: " + schema);
        }
    }

}

