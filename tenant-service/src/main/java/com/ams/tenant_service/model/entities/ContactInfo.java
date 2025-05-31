package com.ams.tenant_service.model.entities;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class ContactInfo {

    private String email;
    private String phone;
    private String address;

}
