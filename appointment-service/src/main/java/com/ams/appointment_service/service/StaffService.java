package com.ams.appointment_service.service;

import com.ams.appointment_service.dto.AppointmentResponseDTO;
import com.ams.appointment_service.dto.staffdto.UpdateDTO;
import com.ams.appointment_service.mapper.AppointmentMapper;
import com.ams.appointment_service.mapper.UpdateResponseMapper;
import com.ams.appointment_service.model.entities.Appointment;
import com.ams.appointment_service.model.entities.StaffScheduleSnapshot;
import com.ams.appointment_service.model.constant.AppointmentStatus;
import com.ams.appointment_service.repository.AppointmentRepository;
import com.ams.appointment_service.repository.StaffScheduleSnapshotRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class StaffService {

    private final StaffScheduleSnapshotRepository staffRepository;
    private final AppointmentRepository appointmentRepository;
    private final NotificationService notificationService;

    public Optional<StaffScheduleSnapshot> getStaffSnapShot(UUID staffId) {
        return staffRepository.findById(staffId);
    }

    public void updateStaffSchedule(StaffScheduleSnapshot snapshot) {
        staffRepository.save(snapshot);
    }

    public void confirmAppointment(long appointmentId, boolean accepted) {
        Optional<Appointment> optional = appointmentRepository.findById(appointmentId);
        if (optional.isEmpty()) return;
        Appointment appointment = optional.get();
        appointment.setStaffStatus(accepted ? AppointmentStatus.APPROVED : AppointmentStatus.DECLINED);
        appointmentRepository.save(appointment);
        notificationService.notifyCustomerAppointmentBookingWasConfirmed(appointment);
    }

    public void confirmAppointmentReschedule(long appointmentId, boolean accepted) {
        Optional<Appointment> optional = appointmentRepository.findById(appointmentId);
        if (optional.isEmpty()) return;
        Appointment appointment = optional.get();
        appointment.setStaffStatus(accepted ? AppointmentStatus.APPROVED : AppointmentStatus.DECLINED);
        appointmentRepository.save(appointment);
        notificationService.notifyCustomerReschedulingWasComFirm(appointment);
    }

    public UpdateDTO editAppointment(UpdateDTO updateRequest) {
        Optional<Appointment> optional = appointmentRepository.findById(updateRequest.getId());
        if (optional.isEmpty()) return null;
        Appointment appointment = optional.get();
        appointment.setNotes(updateRequest.getNotes());
        appointment.setDate(updateRequest.getDate());
        appointment.setStartTime(updateRequest.getStartTime());
        appointment.setEndTime(updateRequest.getEndTime());
        appointment.setStaffStatus(updateRequest.getStatus());
        var updatedAppointment = appointmentRepository.save(appointment);
        notificationService.notifyCustomerAppointmentWasRescheduled(appointment);
        return UpdateResponseMapper.toDTO(updatedAppointment);
    }

    public void deleteAppointment(long appointmentId, UUID staffId) {
        Optional<Appointment> optional = appointmentRepository.findById(appointmentId);
        if (optional.isEmpty()) return;
        Appointment appointment = optional.get();
        long aptId = appointmentRepository.deleteByIdAndStaffId(appointmentId, staffId);
        if (aptId != 0) {
            notificationService.notifyCustomerAppointmentHasBeenCancelled(appointment);
        }
    }

    public List<AppointmentResponseDTO> getAllAppointmentForAStaff(UUID staffId) {
        Optional<StaffScheduleSnapshot> staff = staffRepository.findById(staffId);
        return staff.map(staffScheduleSnapshot -> staffScheduleSnapshot.getAppointments()
                .stream().map(AppointmentMapper::toDTO).toList()).orElse(Collections.emptyList());
    }

}


