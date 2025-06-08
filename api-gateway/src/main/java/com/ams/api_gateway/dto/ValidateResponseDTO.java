package com.ams.api_gateway.dto;

import lombok.Data;

@Data
public class ValidateResponseDTO {
    private final String tenantId;
    private final String userId;
    private final String userRole;
}
