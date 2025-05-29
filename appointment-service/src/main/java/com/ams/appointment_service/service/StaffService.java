package com.ams.appointment_service.service;

import com.ams.appointment_service.dto.AppointmentResponseDTO;
import com.ams.appointment_service.dto.staffdto.UpdateDTO;
import com.ams.appointment_service.exception.TenantNotFoundException;
import com.ams.appointment_service.mapper.AppointmentMapper;
import com.ams.appointment_service.mapper.UpdateResponseMapper;
import com.ams.appointment_service.model.entities.Appointment;
import com.ams.appointment_service.model.entities.StaffScheduleSnapshot;
import com.ams.appointment_service.model.constant.AppointmentStatus;
import com.ams.appointment_service.multitenancy.schema.schema_resolver.TenantContext;
import com.ams.appointment_service.repository.AppointmentRepository;
import com.ams.appointment_service.repository.StaffScheduleSnapshotRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.*;

@Slf4j
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
        try {
            Optional<Appointment> optional = appointmentRepository.findById(appointmentId);
            if (optional.isEmpty()) return;
            Appointment appointment = optional.get();
            appointment.setStaffStatus(accepted ? AppointmentStatus.APPROVED : AppointmentStatus.DECLINED);
            appointmentRepository.save(appointment);
            notificationService.notifyCustomerAppointmentBookingWasConfirmed(appointment);
        } catch (DataAccessException e) {
            log.info("ConfirmAppointment -> Unknown error {}", e.getMessage());
        } finally { TenantContext.INSTANCE.clear(); }
    }

    public void confirmAppointmentReschedule(long appointmentId, boolean accepted) {
        try {
            Optional<Appointment> optional = appointmentRepository.findById(appointmentId);
            if (optional.isEmpty()) return;
            Appointment appointment = optional.get();
            appointment.setStaffStatus(accepted ? AppointmentStatus.APPROVED : AppointmentStatus.DECLINED);
            appointmentRepository.save(appointment);
            notificationService.notifyCustomerReschedulingWasComFirm(appointment);
        } catch (DataAccessException e) {
            log.info("ConfirmAppointmentReschedule -> Unknown error {}", e.getMessage());
        } finally { TenantContext.INSTANCE.clear(); }
    }

    public UpdateDTO editAppointment(UpdateDTO updateRequest) {
        try {
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
        } catch (DataAccessException e) {
            log.info("EditAppointment -> Unknown error {}", e.getMessage());
        } finally { TenantContext.INSTANCE.clear(); }
        throw new TenantNotFoundException("Invalid X-Tenant-ID value: " + TenantContext.INSTANCE.getCurrentTenant());
    }

    public void deleteAppointment(long appointmentId, UUID staffId) {
        try {
            Optional<Appointment> optional = appointmentRepository.findById(appointmentId);
            if (optional.isEmpty()) return;
            Appointment appointment = optional.get();
            long aptId = appointmentRepository.deleteByIdAndStaffId(appointmentId, staffId);
            if (aptId != 0) {
                notificationService.notifyCustomerAppointmentHasBeenCancelled(appointment);
            }
        } catch (DataAccessException e) {
            log.info("DeleteAppointment -> Unknown error {}", e.getMessage());
        } finally { TenantContext.INSTANCE.clear(); }
    }

    public List<AppointmentResponseDTO> getAllAppointmentForAStaff(UUID staffId) {
        try {
            Optional<StaffScheduleSnapshot> staff = staffRepository.findById(staffId);
            return staff.map(staffScheduleSnapshot -> staffScheduleSnapshot.getAppointments()
                    .stream().map(AppointmentMapper::toDTO).toList()).orElse(Collections.emptyList());
        } catch (DataAccessException e) {
            log.info("GetAllAppointmentForAStaff -> Unknown error {}", e.getMessage());
        } finally { TenantContext.INSTANCE.clear(); }
        throw new TenantNotFoundException("Invalid X-Tenant-ID value: " + TenantContext.INSTANCE.getCurrentTenant());
    }

}


