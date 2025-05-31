package com.ams.tenant_service.grpc.mapper;

import com.ams.tenant_service.model.entities.tenant.Staff;
import com.ams.tenant_service.multitenancy.schema.schema_resolver.TenantContext;
import com.google.type.Date;
import staff.CustomSchedule;
import staff.DayOfWeek;
import staff.StaffScheduleSnapshot;
import staff.WeeklySchedule;

public class StaffMapper {

    public static StaffScheduleSnapshot toDTO(Staff staff) {
        return StaffScheduleSnapshot.newBuilder()
                .setTenantId(TenantContext.INSTANCE.getCurrentTenant())
                .setId(staff.getId().toString())
                .setEmail(staff.getEmail())
                .setName(staff.getFirstName())
                .addAllCustomSchedules(staff.getCustomSchedules().stream().map(StaffMapper::toDTO).toList())
                .addAllWeeklySchedule(staff.getWeeklySchedule().stream().map(StaffMapper::toDTO).toList())
                .build();
    }

    public static CustomSchedule toDTO(com.ams.tenant_service.model.entities.tenant.CustomSchedule customSchedule) {
        return CustomSchedule.newBuilder()
          .setId(customSchedule.getId())
          .setDate(Date.newBuilder()
             .setYear(customSchedule.getDate().getYear())
             .setMonth(customSchedule.getDate().getMonthValue())
             .setDay(customSchedule.getDate().getDayOfMonth())
             .build())
          .setStartTime(customSchedule.getStartTime().toString())
          .setEndTime(customSchedule.getEndTime().toString())
          .setIsAvailable(customSchedule.isAvailable())
          .build();
    }

    public static WeeklySchedule toDTO(com.ams.tenant_service.model.entities.tenant.WeeklySchedule weeklySchedule) {
        return WeeklySchedule.newBuilder()
             .setId(weeklySchedule.getId())
             .setDayOfWeek(getDayOfTheWeek(weeklySchedule.getDayOfWeek()))
             .setStartTime(weeklySchedule.getStartTime().toString())
             .setEndTime(weeklySchedule.getEndTime().toString())
             .setIsWorkingDay(weeklySchedule.isWorkingDay())
             .build();
    }

    private static DayOfWeek getDayOfTheWeek(java.time.DayOfWeek day) {
        return switch (day) {
            case MONDAY -> DayOfWeek.MONDAY;
            case TUESDAY -> DayOfWeek.TUESDAY;
            case WEDNESDAY -> DayOfWeek.WEDNESDAY;
            case THURSDAY -> DayOfWeek.THURSDAY;
            case FRIDAY -> DayOfWeek.FRIDAY;
            case SATURDAY -> DayOfWeek.SATURDAY;
            case SUNDAY -> DayOfWeek.SUNDAY;
        };
    }

}
