package com.ams.auth_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequestDTO {
    @NotNull(message = "Tenant id is required")
    @Size(min = 3, message = "Tenant id should be 3 characters or more")
    private String tenantId;

    @NotNull(message = "Email is required")
    @Email
    private String email;

    @NotNull(message = "Password is required")
    private String password;
}
