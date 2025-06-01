package com.ams.tenant_service.multitenancy.schema.config;

import com.ams.tenant_service.multitenancy.schema.StaffFilter;
import com.ams.tenant_service.multitenancy.schema.TenantFilter;
import com.ams.tenant_service.multitenancy.schema.schema_resolver.SchemaMultiTenantConnectionProvider;
import com.ams.tenant_service.multitenancy.schema.schema_resolver.SchemaTenantIdentifierResolver;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
import java.util.HashMap;

@Slf4j
@Configuration
public class MultiTenancyConfig {

    @Bean
    public FilterRegistrationBean<TenantFilter> filterTenantFilterRegistrationBean(TenantFilter filter) {
        var registration = new FilterRegistrationBean<>(filter);
        registration.setOrder(1);
        registration.addUrlPatterns("/tenant/*");
        return registration;
    }

    @Bean
    public FilterRegistrationBean<StaffFilter> filterStaffFilterRegistrationBean(StaffFilter filter) {
        var registration = new FilterRegistrationBean<>(filter);
        registration.setOrder(2);
        registration.addUrlPatterns("/staff/*");
        return registration;
    }

    @Bean
    public MultiTenantConnectionProvider multiTenantConnectionProvider(DataSource dataSource) {
        return new SchemaMultiTenantConnectionProvider(dataSource);
    }

    @Bean
    public CurrentTenantIdentifierResolver tenantIdentifierResolver() {
        return new SchemaTenantIdentifierResolver();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource,
            MultiTenantConnectionProvider multiTenantConnectionProvider,
            CurrentTenantIdentifierResolver tenantIdentifierResolver
    ) {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSource);
        entityManager.setPackagesToScan("com.ams.tenant_service.model.entities");

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        entityManager.setJpaVendorAdapter(jpaVendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.multiTenancy", "SCHEMA");
        properties.put("hibernate.multi_tenant_connection_provider", multiTenantConnectionProvider);
        properties.put("hibernate.tenant_identifier_resolver", tenantIdentifierResolver);
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        entityManager.setJpaPropertyMap(properties);

        return entityManager;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
