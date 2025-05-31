package com.ams.tenant_service.model.constant;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentResponseDTO {
    public String id;
    public String clientName;
    public String clientEmail;
    public String notes;
    public AppointmentStatus customerStatus;
    public AppointmentStatus staffStatus;
    public LocalDate date;
    public LocalTime startTime;
    public LocalTime endTime;
}


