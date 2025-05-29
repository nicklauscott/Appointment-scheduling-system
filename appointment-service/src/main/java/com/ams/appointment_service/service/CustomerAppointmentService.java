package com.ams.appointment_service.service;

import com.ams.appointment_service.dto.AppointmentRequestDTO;
import com.ams.appointment_service.dto.AppointmentResponseDTO;
import com.ams.appointment_service.dto.RescheduleRequestDTO;
import com.ams.appointment_service.exception.AppointmentNotFoundException;
import com.ams.appointment_service.exception.AppointmentTimeSlotException;
import com.ams.appointment_service.exception.NoStaffAvailableException;
import com.ams.appointment_service.mapper.AppointmentMapper;
import com.ams.appointment_service.model.entities.Appointment;
import com.ams.appointment_service.model.entities.StaffScheduleSnapshot;
import com.ams.appointment_service.model.TimeSlot;
import com.ams.appointment_service.model.constant.StaffConfirmStatus;
import com.ams.appointment_service.repository.AppointmentRepository;
import com.ams.appointment_service.repository.StaffScheduleSnapshotRepository;
import com.ams.appointment_service.util.SlotUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@AllArgsConstructor
public class CustomerAppointmentService {

    private final SlotService slotService;
    private final StaffScheduleSnapshotRepository staffRepository;
    private final AppointmentRepository appointmentRepository;
    private final KafkaService kafkaService;

    public AppointmentResponseDTO bookAppointment(AppointmentRequestDTO appointmentRequestDTO) {
        if (appointmentRequestDTO.getDurationInMinutes() > 120) {
            throw new AppointmentTimeSlotException("Appointment duration too long");
        }

        Map<UUID, List<TimeSlot>> availableSlots = slotService.availableSlots();
        if (availableSlots.isEmpty()) {
            throw new NoStaffAvailableException("No available spot. Try again later");
        }

        var staffIdAndTimeSlot = slotService.getFirstAvailableStaff(availableSlots, appointmentRequestDTO.getDurationInMinutes());
        if (staffIdAndTimeSlot == null) {
            throw new NoStaffAvailableException("No available spot. Try again later");
        }

        StaffScheduleSnapshot staff = staffRepository
                .findById(staffIdAndTimeSlot.getKey())
                .orElseThrow(() -> new NoStaffAvailableException("No available spot. Try again later"));

        Appointment appointment = getAppointment(appointmentRequestDTO, staffIdAndTimeSlot, staff);
        Appointment savedAppointment = appointmentRepository.save(appointment);

        // Notify customer and staff
        kafkaService.sendAppointmentBookedNotification(savedAppointment);

        System.out.println("Appointment booked");
        return AppointmentMapper.toDTO(savedAppointment);
    }

    public void cancelAppointment(long appointmentId, String email) {
        Optional<Appointment> optional = appointmentRepository.findByIdAndCustomerEmail(appointmentId, email);
        if (optional.isEmpty()) {
            throw new AppointmentNotFoundException("Appointment with the id " + appointmentId + " not found");
        }
        appointmentRepository.deleteById(appointmentId);
        //notify staff
        kafkaService.notifyStaffAppointmentCancelled(optional.get());
    }

    public void reschedule(long appointmentId, RescheduleRequestDTO request) {
        System.out.println("Rescheduling...");
        Optional<Appointment> appointment = appointmentRepository.findByIdAndCustomerEmail(appointmentId, request.getEmail());
        if (appointment.isEmpty()) {
            throw new AppointmentNotFoundException("Appointment with the id " + appointmentId + " not found");
        }

        if (request.getStartTime().isAfter(request.getEndTime())) {
            throw new AppointmentTimeSlotException("Invalid appointment time");
        }

        if (SlotUtils.startAndEndTimeToMinute(new TimeSlot(request.getStartTime(), request.getEndTime())) > 120) {
            throw new AppointmentTimeSlotException("Appointment time too long");
        }

        // Check if there's an opening
        var availableStaffs = slotService.availableSlots();

        // Find a staff with enough free slots to fit in the customer
        UUID availableStaff = getAvailableStaffId(request, availableStaffs);

        if (availableStaff == null) {
            if (!availableStaffs.isEmpty()) {
                Optional<List<TimeSlot>> firstValidList = availableStaffs.values().stream()
                        .filter(list -> list != null && list.size() > 1)
                        .findFirst();

                if (firstValidList.isPresent()) {
                    var timeSlots = firstValidList.get();
                    var first = timeSlots.getFirst();
                    var last = timeSlots.getLast();
                    throw new AppointmentTimeSlotException(
                      "No available staff. Try time slot between " + first.getStart() + " and " + last.getEnd()
                    );
                } else throw new AppointmentTimeSlotException("No available staff");
            }
            return;
        }

        // reschedule and update appointment after staff confirmation
        Appointment updatedAppointment = appointment.get();
        updatedAppointment.setDate(request.getDate());
        updatedAppointment.setStartTime(request.getStartTime());
        updatedAppointment.setEndTime(request.getEndTime());
        updatedAppointment.setStatus(StaffConfirmStatus.PENDING);
        Appointment savedAppointment = appointmentRepository.save(updatedAppointment);

        // Send APPOINTMENT_RESCHEDULED notification for staff confirmation.
        kafkaService.sendRescheduleRequestNotification(savedAppointment);
    }

    private static UUID getAvailableStaffId(
            RescheduleRequestDTO rescheduleRequestDTO, Map<UUID, List<TimeSlot>> availableStaffs
    ) {
        UUID availableStaff = null;
        for (Map.Entry<UUID, List<TimeSlot>> entry : availableStaffs.entrySet()) {
            System.out.println("Slots ");
            entry.getValue().forEach(System.out::println);
            if (entry.getValue().size() > 1) {
                var start = entry.getValue().getFirst().getStart();
                var end = entry.getValue().getLast().getEnd();
                if (start.isBefore(rescheduleRequestDTO.getStartTime())
                        && end.isAfter(rescheduleRequestDTO.getEndTime())) {
                    availableStaff = entry.getKey();
                }
            }
        }
        return availableStaff;
    }

    private static Appointment getAppointment(
            AppointmentRequestDTO appointmentRequestDTO,
            AbstractMap.SimpleEntry<UUID, TimeSlot> staffIdAndTimeSlot,
            StaffScheduleSnapshot staff
    ) {
        TimeSlot availableTimeSlot = staffIdAndTimeSlot.getValue();
        Appointment appointment = new Appointment();
        appointment.setStaff(staff);
        appointment.setStartTime(availableTimeSlot.getStart());
        appointment.setEndTime(availableTimeSlot.getEnd());
        appointment.setDate(appointmentRequestDTO.getDate());
        appointment.setCustomerName(appointmentRequestDTO.getName());
        appointment.setCustomerEmail(appointmentRequestDTO.getEmail());
        appointment.setNotes(appointmentRequestDTO.getNotes());
        return appointment;
    }
}
