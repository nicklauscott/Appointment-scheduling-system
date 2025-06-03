package com.ams.tenant_service.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AppointmentActionRequestDTO {
    @NotNull(message = "Appointment Id is required")
    String appointmentId;
    @NotNull(message = "Confirm field is required")
    boolean confirm;
}
