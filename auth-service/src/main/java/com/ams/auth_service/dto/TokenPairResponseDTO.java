package com.ams.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenPairResponseDTO {
    private String access;
    private String refresh;
}
