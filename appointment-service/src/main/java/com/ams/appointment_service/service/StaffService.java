package com.ams.appointment_service.service;

import com.ams.appointment_service.dto.AppointmentResponseDTO;
import com.ams.appointment_service.dto.staffdto.UpdateDTO;
import com.ams.appointment_service.mapper.AppointmentMapper;
import com.ams.appointment_service.mapper.UpdateResponseMapper;
import com.ams.appointment_service.model.entities.Appointment;
import com.ams.appointment_service.model.entities.StaffScheduleSnapshot;
import com.ams.appointment_service.model.constant.StaffConfirmStatus;
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

    public Optional<StaffScheduleSnapshot> getStaffSnapShot(UUID staffId) {
        return staffRepository.findById(staffId);
    }

    public void updateStaffSchedule(StaffScheduleSnapshot snapshot) {
        staffRepository.save(snapshot);
    }

    public void confirmAppointment(long appointmentId) {
        Optional<Appointment> optional = appointmentRepository.findById(appointmentId);
        if (optional.isEmpty()) return;
        Appointment appointment = optional.get();
        appointment.setStatus(StaffConfirmStatus.APPROVED);
        appointmentRepository.save(appointment);
    }

    public UpdateDTO editAppointment(UpdateDTO updateRequest) {
        Optional<Appointment> optional = appointmentRepository.findById(updateRequest.getId());
        if (optional.isEmpty()) return null;
        Appointment appointment = optional.get();
        appointment.setNotes(updateRequest.getNotes());
        appointment.setDate(updateRequest.getDate());
        appointment.setStartTime(updateRequest.getStartTime());
        appointment.setEndTime(updateRequest.getEndTime());
        appointment.setStatus(updateRequest.getStatus());
        var updatedAppointment = appointmentRepository.save(appointment);
        return UpdateResponseMapper.toDTO(updatedAppointment);
    }

    public void deleteAppointment(long appointmentId, UUID staffId) {
        appointmentRepository.deleteByIdAndStaffId(appointmentId, staffId);
    }

    public List<AppointmentResponseDTO> getAllAppointmentForAStaff(UUID staffId) {
        Optional<StaffScheduleSnapshot> staff = staffRepository.findById(staffId);
        return staff.map(staffScheduleSnapshot -> staffScheduleSnapshot.getAppointments()
                .stream().map(AppointmentMapper::toDTO).toList()).orElse(Collections.emptyList());
    }



}


