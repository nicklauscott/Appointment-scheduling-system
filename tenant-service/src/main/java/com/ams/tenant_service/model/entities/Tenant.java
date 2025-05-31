package com.ams.tenant_service.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Map;

@Entity
@Table(name = "tenants")
@Data
public class Tenant {
    @Id
    @Column(name = "tenant_id")
    private String tenantId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "primary_color")
    private String primaryColor;

    @Embedded
    private NotificationPreferences notificationPreferences;

    @Column(name = "domain")
    private String domain;

    @Embedded
    private ContactInfo contactInfo;

    @ElementCollection
    @CollectionTable(name = "tenant_custom_config", joinColumns = @JoinColumn(name = "tenant_id"))
    @MapKeyColumn(name = "config_key")
    @Column(name = "config_value")
    private Map<String, String> customConfig;
}
