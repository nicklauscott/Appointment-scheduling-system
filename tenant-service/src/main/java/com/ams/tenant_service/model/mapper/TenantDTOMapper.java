package com.ams.tenant_service.model.mapper;

import com.ams.tenant_service.model.dto.tenant.TenantDTO;
import com.ams.tenant_service.model.entities.ContactInfo;
import com.ams.tenant_service.model.entities.NotificationPreferences;
import com.ams.tenant_service.model.entities.Tenant;
import com.ams.tenant_service.multitenancy.schema.schema_resolver.TenantContext;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TenantDTOMapper {

    public static TenantDTO toDTO(Tenant tenant) {
        TenantDTO tenantDTO = new TenantDTO();
        tenantDTO.setTenantId(TenantContext.INSTANCE.getRequestDetail().get("X-Tenant-ID"));
        if (tenant.getCompanyName() != null) tenantDTO.setCompanyName(tenant.getCompanyName());
        if (tenant.getTenantId() != null) tenantDTO.setLogoUrl(tenant.getLogoUrl());
        if (tenant.getPrimaryColor() != null) tenantDTO.setPrimaryColor(tenant.getPrimaryColor());
        if (tenant.getDomain() != null) tenantDTO.setDomain(tenant.getDomain());
        if (tenant.getContactInfo() != null) tenantDTO.setCustomConfig(tenant.getCustomConfig());
        if (tenant.getContactInfo() != null) tenantDTO.setContactInfo(
           new com.ams.tenant_service.model.dto.tenant.ContactInfo(
              (tenant.getContactInfo().getEmail() != null) ? tenant.getContactInfo().getEmail() : "",
              (tenant.getContactInfo().getPhone() != null) ? tenant.getContactInfo().getPhone() : "",
              (tenant.getContactInfo().getAddress() != null) ? tenant.getContactInfo().getAddress() : "")
        );
        if (tenant.getNotificationPreferences() != null) tenantDTO.setNotificationPreferences(
           new com.ams.tenant_service.model.dto.tenant.NotificationPreferences(
             (tenant.getNotificationPreferences().getSenderEmail() != null)
                     ? tenant.getNotificationPreferences().getSenderEmail() : "",
             (tenant.getNotificationPreferences().getTemplates() != null)
                     ? tenant.getNotificationPreferences().getTemplates() : Collections.emptyMap()
           )
        );
        return tenantDTO;
    }

    public static Tenant toEntity(TenantDTO dto) {
        Tenant tenant = new Tenant();
        tenant.setTenantId(TenantContext.INSTANCE.getRequestDetail().get("X-Tenant-ID"));
        if (dto.getCompanyName() != null) tenant.setCompanyName(dto.getCompanyName());
        if (dto.getLogoUrl() != null) tenant.setLogoUrl(dto.getLogoUrl());
        if (dto.getPrimaryColor() != null) tenant.setPrimaryColor(dto.getPrimaryColor());
        if (dto.getDomain() != null) tenant.setDomain(dto.getDomain());
        tenant.setCustomConfig(updateMap(tenant.getCustomConfig(), dto.getCustomConfig()));
        if (dto.getContactInfo() != null) tenant.setContactInfo(getContactInfo(dto, tenant));
        if (dto.getNotificationPreferences() != null)
            tenant.setNotificationPreferences(getNotificationPreferences(dto, tenant));
        return tenant;
    }

    private static NotificationPreferences getNotificationPreferences(TenantDTO dto, Tenant tenant) {
        NotificationPreferences notification = new NotificationPreferences();
        if (dto.getNotificationPreferences().getSenderEmail() != null)
            notification.setSenderEmail(dto.getNotificationPreferences().getSenderEmail());
        if (dto.getNotificationPreferences().getTemplates() != null)
            notification.setTemplates(updateMap(tenant.getNotificationPreferences().getTemplates(),
                    dto.getNotificationPreferences().getTemplates()));
        return notification;
    }

    private static ContactInfo getContactInfo(TenantDTO dto, Tenant tenant) {
        ContactInfo contact = new ContactInfo();
        contact.setEmail((dto.getContactInfo().getEmail() == null)
                ? tenant.getContactInfo().getEmail() : dto.getContactInfo().getEmail());
        contact.setPhone((dto.getContactInfo().getPhone() == null)
                ? tenant.getContactInfo().getPhone() : dto.getContactInfo().getPhone());
        contact.setAddress((dto.getContactInfo().getAddress() == null)
                ? tenant.getContactInfo().getAddress() : dto.getContactInfo().getAddress());
        return contact;
    }

    public static Map<String, String> updateMap(Map<String, String> oldData, Map<String, String> newData) {
        Map<String, String> updatedMap = new HashMap<>(oldData);
        if (!newData.isEmpty()) updatedMap.putAll(newData);
        return updatedMap;
    }

}

