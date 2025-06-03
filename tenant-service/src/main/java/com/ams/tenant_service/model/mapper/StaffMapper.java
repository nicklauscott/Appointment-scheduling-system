package com.ams.tenant_service.model.mapper;

import com.ams.tenant_service.model.dto.staff.CustomScheduleDTO;
import com.ams.tenant_service.model.dto.staff.StaffResponseDTO;
import com.ams.tenant_service.model.dto.staff.WeeklyScheduleDTO;
import com.ams.tenant_service.model.entities.tenant.CustomSchedule;
import com.ams.tenant_service.model.entities.tenant.Staff;
import com.ams.tenant_service.model.entities.tenant.WeeklySchedule;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StaffMapper {

    public static StaffResponseDTO toDTO(Staff staff) {
        StaffResponseDTO response = new StaffResponseDTO();
        response.setEmail(staff.getEmail());
        response.setFirstName(staff.getFirstName());
        response.setLastName(staff.getLastName());
        response.setGender(staff.getGender());
        response.setMobile(staff.getMobile());
        response.setAddress(staff.getAddress());
        response.setProfilePictureUrl(staff.getProfilePictureUrl());
        response.setDateOfBirth(staff.getDateOfBirth());
        response.setWeeklySchedule(staff.getWeeklySchedule().stream().map(StaffMapper::toDTO).toList());
        response.setCustomSchedules(staff.getCustomSchedules().stream().map(StaffMapper::toDTO).toList());
        return response;
    }

    public static WeeklyScheduleDTO toDTO(WeeklySchedule schedule) {
        WeeklyScheduleDTO weeklySchedule = new WeeklyScheduleDTO();
        weeklySchedule.setDayOfWeek(schedule.getDayOfWeek());
        weeklySchedule.setStartTime(schedule.getStartTime());
        weeklySchedule.setEndTime(schedule.getEndTime());
        weeklySchedule.setWorkingDay(schedule.isWorkingDay());
        return weeklySchedule;
    }

    public static CustomScheduleDTO toDTO(CustomSchedule schedule) {
        CustomScheduleDTO customSchedule = new CustomScheduleDTO();
        customSchedule.setDate(schedule.getDate());
        customSchedule.setStartTime(schedule.getStartTime());
        customSchedule.setEndTime(schedule.getEndTime());
        customSchedule.setAvailable(schedule.isAvailable());
        return customSchedule;
    }

    public static WeeklySchedule toEntity(Staff staff, WeeklyScheduleDTO request) {
        WeeklySchedule schedule = new WeeklySchedule();
        schedule.setDayOfWeek(request.getDayOfWeek());
        schedule.setStartTime(request.getStartTime());
        schedule.setEndTime(request.getEndTime());
        schedule.setWorkingDay(request.isWorkingDay());
        schedule.setStaff(staff);
        return schedule;
    }

    public static CustomSchedule toEntity(Staff staff, CustomScheduleDTO request) {
        CustomSchedule customSchedule = new CustomSchedule();
        customSchedule.setDate(request.getDate());
        customSchedule.setStartTime(request.getStartTime());
        customSchedule.setEndTime(request.getEndTime());
        customSchedule.setAvailable(request.isAvailable());
        customSchedule.setStaff(staff);
        return customSchedule;
    }
}
