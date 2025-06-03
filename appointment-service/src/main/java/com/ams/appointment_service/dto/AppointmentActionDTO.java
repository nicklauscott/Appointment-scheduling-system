package com.ams.appointment_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AppointmentActionDTO {
    @NotNull private String email;
    @NotNull private long appointmentId;
}
