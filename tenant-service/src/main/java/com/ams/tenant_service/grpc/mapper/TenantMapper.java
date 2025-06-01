package com.ams.tenant_service.grpc.mapper;

import tenant.ContactInfo;
import tenant.NotificationPreferences;
import tenant.Tenant;

import java.util.Collections;

public class TenantMapper {

    public static Tenant toTenant(com.ams.tenant_service.model.entities.Tenant tenant) {
        return Tenant.newBuilder()
                .setTenantId(tenant.getTenantId())
                .setCompanyName((tenant.getCompanyName() == null) ? "" : tenant.getCompanyName())
                .setLogoUrl((tenant.getLogoUrl() == null) ? "" : tenant.getLogoUrl())
                .setPrimaryColor((tenant.getPrimaryColor() == null) ? "" : tenant.getPrimaryColor())
                .setDomain((tenant.getDomain() == null) ? "" : tenant.getDomain())
                .putAllCustomConfig(tenant.getCustomConfig())
                .setNotificationPreferences(NotificationPreferences.newBuilder()
                        .setSenderEmail((tenant.getNotificationPreferences().getSenderEmail() == null) ? "" : tenant.getNotificationPreferences().getSenderEmail())
                        .putAllTemplates(tenant.getNotificationPreferences().getTemplates())
                        .build())
                .setContactInfo(ContactInfo.newBuilder()
                        .setEmail((tenant.getContactInfo().getEmail() == null) ? "" : tenant.getContactInfo().getEmail())
                        .setAddress((tenant.getContactInfo().getAddress() == null) ? "" : tenant.getContactInfo().getAddress())
                        .setPhone((tenant.getContactInfo().getPhone() == null) ? "" : tenant.getContactInfo().getPhone())
                        .build())
                .build();
    }

}
