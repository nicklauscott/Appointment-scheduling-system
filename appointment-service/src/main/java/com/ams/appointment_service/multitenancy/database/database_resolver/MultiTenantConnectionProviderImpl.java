package com.ams.appointment_service.multitenancy.database.database_resolver;

import com.ams.appointment_service.exception.TenantNotFoundException;
import com.ams.appointment_service.multitenancy.database.TenantSchemaInitialization;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.initialization.qual.Initialized;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.UnknownKeyFor;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
//@Component
public class MultiTenantConnectionProviderImpl implements MultiTenantConnectionProvider {

    private final Map<String, HikariDataSource> dataSources = new ConcurrentHashMap<>();

    public MultiTenantConnectionProviderImpl() {
        addTenant("tenant1", "jdbc:postgresql://localhost:5432/tenant1");
        addTenant("tenant2", "jdbc:postgresql://localhost:5432/tenant2");
        addTenant("tyt", "jdbc:postgresql://localhost:5432/tyt");
    }

    public void addTenant(String tenantId, String dbUrl) {
        /*
        var url = dbUrl.startsWith("jdbc:") ? dbUrl : "jdbc:postgresql://localhost:5432/" + dbUrl;
        if (!dataSources.containsKey(tenantId)) {
            HikariDataSource ds = new HikariDataSource();
            ds.setJdbcUrl(url);
            ds.setUsername("postgres");
            ds.setPoolName("Tenant-" + tenantId);
            ds.setDriverClassName("org.postgresql.Driver");
            dataSources.put(tenantId, ds);

            TenantSchemaInitialization.initializeSchema(dataSources.get(tenantId));
        }
         */
    }

    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        HikariDataSource ds = dataSources.get(tenantIdentifier);
        if (ds == null || ds.isClosed()) {
            log.info("DataSource for tenant {} is closed!", tenantIdentifier);
            throw new SQLException("DataSource for tenant [" + tenantIdentifier + "] is not available.");
        }
        return ds.getConnection();
    }


    @Override
    public Connection getAnyConnection() throws SQLException {
        for (HikariDataSource ds : dataSources.values()) {
            if (!ds.isClosed())  {
                log.info("=================Selected db {}", ds.getJdbcUrl());
                return ds.getConnection();
            }
        }
        throw new SQLException("No available tenant data sources");
    }

    @PreDestroy
    public void shutdown() {
        dataSources.values().forEach(HikariDataSource::close);
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public void releaseConnection(String s, Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }

    @Override
    public @UnknownKeyFor @Initialized boolean isUnwrappableAs(@UnknownKeyFor @NonNull @Initialized Class<@UnknownKeyFor @NonNull @Initialized ?> aClass) {
        return false;
    }

    @Override
    public <T> T unwrap(@UnknownKeyFor @NonNull @Initialized Class<T> aClass) {
        return null;
    }
}
