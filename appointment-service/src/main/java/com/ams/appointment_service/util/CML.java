package com.ams.appointment_service.util;

import com.ams.appointment_service.dto.AppointmentRequestDTO;
import com.ams.appointment_service.dto.AppointmentResponseDTO;
import com.ams.appointment_service.dto.RescheduleRequestDTO;
import com.ams.appointment_service.model.entities.Appointment;
import com.ams.appointment_service.model.entities.CustomSchedule;
import com.ams.appointment_service.model.entities.StaffScheduleSnapshot;
import com.ams.appointment_service.model.TimeSlot;
import com.ams.appointment_service.repository.AppointmentRepository;
import com.ams.appointment_service.repository.CustomScheduleRepository;
import com.ams.appointment_service.repository.StaffScheduleSnapshotRepository;
import com.ams.appointment_service.service.CustomerAppointmentService;
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
    private final CustomerAppointmentService customerAppointmentService;

    @Transactional
    @Override
    public void run(String... args) {
        getStaff();
        bookAppointments(1);
        //testCASReschedule(testCASBookApt());
        //System.exit(0);


    }

    private AppointmentResponseDTO testCASBookApt() {
        getStaff();
        bookAppointments(1);
        AppointmentRequestDTO appointmentRequestDTO = new AppointmentRequestDTO();
        appointmentRequestDTO.setDate(LocalDate.now());
        appointmentRequestDTO.setDurationInMinutes(60);
        appointmentRequestDTO.setEmail("Paul@io");
        appointmentRequestDTO.setName("Paul");
        return customerAppointmentService.bookAppointment(appointmentRequestDTO);
    }

    private void testCASReschedule(AppointmentResponseDTO appt) {
        RescheduleRequestDTO requestDTO = new RescheduleRequestDTO();
        requestDTO.setEmail(appt.getClientEmail());
        requestDTO.setDate(LocalDate.now());
        requestDTO.setStartTime(LocalTime.of(11, 0));
        requestDTO.setEndTime(LocalTime.of(12, 0));
        customerAppointmentService.reschedule(appt.getId(), requestDTO);
    }

    private void testReschedule(long appointmentId) {
        getStaff();
        bookAppointments(1);
        RescheduleRequestDTO requestDTO = new RescheduleRequestDTO();
        requestDTO.setEmail("Marca@io.nz");
        requestDTO.setDate(LocalDate.now());
        requestDTO.setStartTime(LocalTime.of(12, 0));
        requestDTO.setEndTime(LocalTime.of(14, 0));
        if (appointmentId < 1) {
            customerAppointmentService.reschedule(1, requestDTO);
        } else customerAppointmentService.reschedule(appointmentId, requestDTO);
    }

    private void testAppointmentWontBook() {
        StaffScheduleSnapshot staff = getStaff();
        assert staff != null;

        try {
            bookAppointments(5);
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        AppointmentRequestDTO request = new AppointmentRequestDTO();
        request.setDate(LocalDate.now());
        request.setDurationInMinutes(300);
        customerAppointmentService.bookAppointment(request);


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
            appointment.setCustomerName("Marca");
            appointment.setCustomerName("Marca@io.nz");
            appointment.setNotes("Marca's barbing");
            appointment.setStaff(staffA);
            appointmentRepository.save(appointment);
        }

        List<TimeSlot> timeSlotsAfter = slotService.getAvailableSlots(dbStaff.getId(), LocalDate.now(), 30);
        System.out.println("Open slots: ");
        timeSlotsAfter.forEach(System.out::println);
    }

    public void bookAppointments(int size) {
        List<StaffScheduleSnapshot> staffList = staffRepository.findAll();
        if (staffList.isEmpty()) {
            System.out.println("Staff list is empty");
            return;
        }
        StaffScheduleSnapshot staff = staffList.getFirst();
        List<TimeSlot> timeSlots = slotService.getAvailableSlots(staff.getId(), LocalDate.now(), 30);
        for (int i = 0; i < size; i++) {
            TimeSlot timeSlot = timeSlots.get(i);
            Appointment appointment = new Appointment();
            appointment.setDate(LocalDate.now());
            appointment.setStartTime(timeSlot.getStart());
            appointment.setEndTime(timeSlot.getEnd());
            appointment.setCustomerName("Marca");
            appointment.setCustomerEmail("Marca@io.nz");
            appointment.setNotes("Marca's barbing");
            appointment.setStaff(staff);
            System.out.println("CML -> bookAppointments: saving appointment");
            appointmentRepository.save(appointment);
        }
    }

    private StaffScheduleSnapshot getStaff() {
        StaffScheduleSnapshot staffA = new StaffScheduleSnapshot();
        staffRepository.save(staffA);
        List<StaffScheduleSnapshot> staffList = staffRepository.findAll();
        if (staffList.isEmpty()) {
            System.out.println("Staff list is empty");
            return null;
        }
        StaffScheduleSnapshot dbStaff = staffList.getFirst();
        CustomSchedule schedule = new CustomSchedule();
        schedule.setDate(LocalDate.now());
        schedule.setStartTime(LocalTime.of(9, 0));
        schedule.setEndTime(LocalTime.of(17, 0));
        schedule.setAvailable(true);
        schedule.setStaff(dbStaff);
        scheduleRepository.save(schedule);
        return dbStaff;
    }
}