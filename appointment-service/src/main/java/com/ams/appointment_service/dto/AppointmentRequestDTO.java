package com.ams.appointment_service.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AppointmentRequestDTO {
    private LocalDate date;
    private int durationInMinutes;
    private String name;
    private String email;
    private String notes;
}
