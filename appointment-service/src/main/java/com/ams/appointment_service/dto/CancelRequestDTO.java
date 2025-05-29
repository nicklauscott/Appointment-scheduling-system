package com.ams.appointment_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancelRequestDTO {
    private long id;
    private String email;
}
