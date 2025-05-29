package com.ams.appointment_service.dto;

import com.ams.appointment_service.model.constant.StaffConfirmStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class AppointmentResponseDTO {
    private long id;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String clientName;
    private String clientEmail;
    private String notes;
    private StaffConfirmStatus status;
}
