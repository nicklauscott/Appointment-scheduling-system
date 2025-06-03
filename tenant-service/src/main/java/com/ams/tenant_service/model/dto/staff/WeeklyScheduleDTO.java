package com.ams.tenant_service.model.dto.staff;

import lombok.Data;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
public class WeeklyScheduleDTO {
   private DayOfWeek dayOfWeek;
   private LocalTime startTime;
   private LocalTime endTime;
   private boolean isWorkingDay;
}





