package com.ams.appointment_service.service;

import com.ams.appointment_service.model.*;
import com.ams.appointment_service.model.entities.Appointment;
import com.ams.appointment_service.model.entities.CustomSchedule;
import com.ams.appointment_service.model.entities.StaffScheduleSnapshot;
import com.ams.appointment_service.model.entities.WeeklySchedule;
import com.ams.appointment_service.repository.AppointmentRepository;
import com.ams.appointment_service.repository.CustomScheduleRepository;
import com.ams.appointment_service.repository.WeeklyScheduleRepository;
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

    public Map<UUID, List<TimeSlot>> availableSlots() {
        List<StaffScheduleSnapshot> allStaffs = staffRepository.findAll();
        Map<UUID, List<TimeSlot>> availableTimeSlots = new HashMap<>();
        for (StaffScheduleSnapshot staff : allStaffs) {
            var slots = getAvailableSlots(staff.getId(), LocalDate.now(), 30);
            availableTimeSlots.put(staff.getId(), slots);
        }
        return availableTimeSlots;
    }

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

    public AbstractMap.SimpleEntry<UUID, TimeSlot> getFirstAvailableStaff(
            Map<UUID, List<TimeSlot>> availableStaffs, int sessionInMinutes
    ) {
        for (Map.Entry<UUID, List<TimeSlot>> entry : availableStaffs.entrySet()) {
            List<TimeSlot> slots
                    = entry.getValue();
            slots.sort(Comparator.comparing(TimeSlot::getStart)); // Ensure sorted

            int accumulatedMinutes = 0;
            LocalTime blockStart = null;
            LocalTime lastEnd = null;

            for (TimeSlot slot : slots) {
                LocalTime start = slot.getStart();
                LocalTime end = slot.getEnd();
                int slotMinutes = (int) ((end.toNanoOfDay() - start.toNanoOfDay()) / 60_000_000_000L);

                if (lastEnd == null || lastEnd.equals(start)) {
                    if (blockStart == null) blockStart = start;
                    accumulatedMinutes += slotMinutes;

                    if (accumulatedMinutes >= sessionInMinutes) {
                        return new AbstractMap.SimpleEntry<>(entry.getKey(), new TimeSlot(blockStart, end));
                    }
                } else {
                    accumulatedMinutes = slotMinutes;
                    blockStart = start;
                }

                lastEnd = end;
            }
        }

        return null;
    }

}


