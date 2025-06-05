package com.ams.auth_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterTenantRequestDTO {
    @NotNull(message = "Tenant id is required")
    @Size(min = 3, message = "Tenant id must be 3 characters or more")
    private String tenantId;

    @NotNull(message = "Tenant name is required")
    @Size(min = 3, message = "Tenant name must be 3 characters or more")
    @NotNull private String tenantName;

    @NotNull(message = "Tenant email is required")
    @Email
    private String tenantEmail;

    @Size(min = 6, message = "Tenant name must be 6 characters or more")
    private String password;
}
