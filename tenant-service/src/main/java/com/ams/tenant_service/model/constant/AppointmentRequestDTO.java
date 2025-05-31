package com.ams.tenant_service.model.constant;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentRequestDTO {
    public String tenantId;
    public String id;
    public String clientName;
    public String clientEmail;
    public String notes;
    public AppointmentStatus staffStatus;
    public LocalDate date;
    public LocalTime startTime;
    public LocalTime endTime;
}


