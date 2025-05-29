package com.ams.appointment_service.service;

import com.ams.appointment_service.model.entities.CustomSchedule;
import com.ams.appointment_service.model.entities.StaffScheduleSnapshot;
import com.ams.appointment_service.model.entities.WeeklySchedule;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class StaffSnapshotMapperService {

    private static StaffScheduleSnapshot snapshot;

    public static StaffScheduleSnapshot toEntity(StaffScheduleSnapshot staffSnapshot, staff.StaffScheduleSnapshot request) {
        snapshot = staffSnapshot;
        snapshot.setEmail(request.getEmail());
        snapshot.setName(request.getName());
        //repository.save(staff); // Persist so CustomSchedule & WeeklySchedule can be added

        List<CustomSchedule> schedules = request.getCustomSchedulesList().stream()
                .map(StaffSnapshotMapperService::toCustomScheduleEntity).toList();

        List<WeeklySchedule> weeklySchedules = request.getWeeklyScheduleList().stream()
                .map(StaffSnapshotMapperService::toWeeklyScheduleEntity).toList();

        snapshot.setCustomSchedules(schedules);
        snapshot.setWeeklySchedule(weeklySchedules);

        return snapshot;
    }

    private static CustomSchedule toCustomScheduleEntity(staff.CustomSchedule request) {
        CustomSchedule schedule = new CustomSchedule();
        schedule.setId(request.getId());
        schedule.setStaff(snapshot);
        var date = request.getDate();
        schedule.setDate(LocalDate.of(date.getYear(), date.getMonth(), date.getDay()));
        schedule.setStartTime(LocalTime.parse(request.getStartTime()));
        schedule.setEndTime(LocalTime.parse(request.getEndTime()));
        schedule.setAvailable(request.getIsAvailable());
        return schedule;
    }

    private static WeeklySchedule toWeeklyScheduleEntity(staff.WeeklySchedule request) {
        WeeklySchedule schedule = new WeeklySchedule();
        schedule.setStaff(snapshot);
        schedule.setId(request.getId());
        schedule.setDayOfWeek(DayOfWeek.of(request.getDayOfWeekValue()));
        schedule.setWorkingDay(request.getIsWorkingDay());
        schedule.setStartTime(LocalTime.parse(request.getStartTime()));
        schedule.setEndTime(LocalTime.parse(request.getEndTime()));
        return schedule;
    }

}
