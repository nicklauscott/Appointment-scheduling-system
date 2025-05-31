package com.ams.tenant_service.grpc.mapper;

import tenant.ContactInfo;
import tenant.NotificationPreferences;
import tenant.Tenant;

public class TenantMapper {

    public static Tenant toTenant(com.ams.tenant_service.model.entities.Tenant tenant) {
        return Tenant.newBuilder()
                .setTenantId(tenant.getTenantId())
                .setCompanyName(tenant.getCompanyName())
                .setLogoUrl(tenant.getLogoUrl())
                .setPrimaryColor(tenant.getPrimaryColor())
                .setDomain(tenant.getDomain())
                .putAllCustomConfig(tenant.getCustomConfig())
                .setNotificationPreferences(NotificationPreferences.newBuilder()
                        .setSenderEmail(tenant.getNotificationPreferences().getSenderEmail())
                        .putAllTemplates(tenant.getNotificationPreferences().getTemplates())
                        .build())
                .setContactInfo(ContactInfo.newBuilder()
                        .setEmail(tenant.getContactInfo().getEmail())
                        .setAddress(tenant.getContactInfo().getAddress())
                        .setPhone(tenant.getContactInfo().getPhone())
                        .build())
                .build();
    }

}
