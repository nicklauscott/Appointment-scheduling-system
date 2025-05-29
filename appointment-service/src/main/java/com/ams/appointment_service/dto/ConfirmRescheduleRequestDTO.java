package com.ams.appointment_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class ConfirmRescheduleRequestDTO {
    private long id;
    private String email;
    private boolean confirm;
}
