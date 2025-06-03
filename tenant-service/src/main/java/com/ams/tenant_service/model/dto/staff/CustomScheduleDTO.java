package com.ams.tenant_service.model.dto.staff;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CustomScheduleDTO {
   private LocalDate date;
   private LocalTime startTime;
   private LocalTime endTime;
   private boolean isAvailable;
}





