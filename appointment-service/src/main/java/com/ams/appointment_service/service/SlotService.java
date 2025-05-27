package com.ams.appointment_service.service;

import com.ams.appointment_service.model.Appointment;
import com.ams.appointment_service.model.CustomSchedule;
import com.ams.appointment_service.model.WeeklySchedule;
import com.ams.appointment_service.repository.AppointmentRepository;
import com.ams.appointment_service.repository.CustomScheduleRepository;
import com.ams.appointment_service.repository.WeeklyScheduleRepository;
import com.ams.appointment_service.model.TimeSlot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import com.ams.appointment_service.repository.StaffScheduleSnapshotRepository;

@Service
@RequiredArgsConstructor
public class SlotService {

    private final StaffScheduleSnapshotRepository staffRepository;
    private final WeeklyScheduleRepository weeklyScheduleRepository;
    private final CustomScheduleRepository customScheduleRepository;
    private final AppointmentRepository appointmentRepository;

    public List<TimeSlot> getAvailableSlots(UUID staffId, LocalDate date, int slotMinutes) {
        staffRepository.findById(staffId).orElseThrow(() -> new IllegalArgumentException("Staff not found"));

        LocalTime workingStart;
        LocalTime workingEnd;

        // 1. Use CustomSchedule if available
        Optional<CustomSchedule> customOpt = customScheduleRepository.findByStaffIdAndDate(staffId, date);
        if (customOpt.isPresent()) {
            CustomSchedule custom = customOpt.get();
            if (!custom.isAvailable()) {
                return Collections.emptyList();
            }
            workingStart = custom.getStartTime();
            workingEnd = custom.getEndTime();
        } else {
            // 2. Fall back to WeeklySchedule
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            WeeklySchedule weekly = weeklyScheduleRepository.findByStaffIdAndDayOfWeek(staffId, dayOfWeek)
                    .orElseThrow(() -> new RuntimeException("Weekly schedule not found"));

            if (!weekly.isWorkingDay()) {
                return Collections.emptyList();
            }

            workingStart = weekly.getStartTime();
            workingEnd = weekly.getEndTime();
        }

        // 3. Fetch existing appointments
        List<Appointment> appointments = appointmentRepository.findByStaffIdAndDate(staffId, date);

        // 4. Generate time slots and filter conflicts
        List<TimeSlot> availableSlots = new ArrayList<>();
        LocalTime slotStart = workingStart;

        while (!slotStart.plusMinutes(slotMinutes).isAfter(workingEnd)) {
            LocalTime slotEnd = slotStart.plusMinutes(slotMinutes);
            TimeSlot slot = new TimeSlot(slotStart, slotEnd);

            boolean overlaps = appointments.stream()
                    .anyMatch(appt -> slot.overlapsWith(appt.getStartTime(), appt.getEndTime()));

            if (!overlaps) {
                availableSlots.add(slot);
            }

            slotStart = slotEnd;
        }

        return availableSlots;
    }
}

