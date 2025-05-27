package com.ams.appointment_service.util;

import com.ams.appointment_service.model.Appointment;
import com.ams.appointment_service.model.CustomSchedule;
import com.ams.appointment_service.model.StaffScheduleSnapshot;
import com.ams.appointment_service.model.TimeSlot;
import com.ams.appointment_service.repository.AppointmentRepository;
import com.ams.appointment_service.repository.CustomScheduleRepository;
import com.ams.appointment_service.repository.StaffScheduleSnapshotRepository;
import com.ams.appointment_service.service.SlotService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CML implements CommandLineRunner {

    private final StaffScheduleSnapshotRepository staffRepository;
    private final CustomScheduleRepository scheduleRepository;
    private final SlotService slotService;
    private final AppointmentRepository appointmentRepository;

    @Transactional
    @Override
    public void run(String... args) {
        testSlutService();
    }

    private void testSlutService() {
        StaffScheduleSnapshot staffA = new StaffScheduleSnapshot();
        staffRepository.save(staffA);

        List<StaffScheduleSnapshot> staffList = staffRepository.findAll();
        System.out.println(staffList);


        if (staffList.isEmpty()) {
            System.out.println("Staff list is empty");
            return;
        }

        StaffScheduleSnapshot dbStaff = staffList.getFirst();

        CustomSchedule schedule = new CustomSchedule();
        schedule.setDate(LocalDate.now());
        schedule.setStartTime(LocalTime.of(9, 0));
        schedule.setEndTime(LocalTime.of(17, 0));
        schedule.setAvailable(true);
        schedule.setStaff(dbStaff);

        scheduleRepository.save(schedule);

        List<TimeSlot> timeSlots = slotService.getAvailableSlots(dbStaff.getId(), LocalDate.now(), 30);

        for (int i = 0; i < 10; i++) {
            TimeSlot timeSlot = timeSlots.get(i);
            Appointment appointment = new Appointment();
            appointment.setDate(LocalDate.now());
            appointment.setStartTime(timeSlot.getStart());
            appointment.setEndTime(timeSlot.getEnd());
            appointment.setClientName("Marca");
            appointment.setClientName("Marca@io.nz");
            appointment.setNotes("Marca's barbing");
            appointment.setStaff(staffA);
            appointmentRepository.save(appointment);
        }

        List<TimeSlot> timeSlotsAfter = slotService.getAvailableSlots(dbStaff.getId(), LocalDate.now(), 30);
        System.out.println("Open slots: ");
        timeSlotsAfter.forEach(System.out::println);
    }
}