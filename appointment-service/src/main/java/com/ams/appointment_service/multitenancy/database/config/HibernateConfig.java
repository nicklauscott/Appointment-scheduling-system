package com.ams.appointment_service.multitenancy.database.config;

import com.ams.appointment_service.multitenancy.database.database_resolver.MultiTenantConnectionProviderImpl;
import com.ams.appointment_service.multitenancy.database.database_resolver.TenantIdentifierResolver;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.HashMap;
import java.util.Map;

import static org.hibernate.tool.schema.TargetType.DATABASE;

//@Configuration
@AllArgsConstructor
public class HibernateConfig {

    private MultiTenantConnectionProviderImpl multiTenantConnectionProvider;
    private TenantIdentifierResolver tenantIdentifierResolver;

    //@Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPackagesToScan("com.ams"); // adjust this to your base package
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.multiTenancy", DATABASE);
        properties.put("hibernate.multi_tenant_connection_provider", multiTenantConnectionProvider);
        properties.put("hibernate.tenant_identifier_resolver", tenantIdentifierResolver);
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

        em.setJpaPropertyMap(properties);
        return em;
    }

}
