package com.ams.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterStaffResponseDTO {
    private String tenantId;
    private String email;
}
