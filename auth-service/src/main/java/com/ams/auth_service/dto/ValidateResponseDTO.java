package com.ams.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidateResponseDTO {
    private final String tenantId;
    private final String userId;
    private final String userRole;
}
