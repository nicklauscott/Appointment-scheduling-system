package com.ams.appointment_service.dto.staffdto;

import com.ams.appointment_service.model.constant.AppointmentStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class UpdateDTO {
    private long id;
    private String notes;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private AppointmentStatus status;
}
