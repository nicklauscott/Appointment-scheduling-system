package com.ams.tenant_service.model.dto.tenant;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactInfo {
    private String email;
    private String phone;
    private String address;
}
