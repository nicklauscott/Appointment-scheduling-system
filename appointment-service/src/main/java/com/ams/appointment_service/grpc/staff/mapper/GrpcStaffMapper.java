package com.ams.appointment_service.grpc.staff.mapper;

import com.ams.appointment_service.dto.AppointmentResponseDTO;
import com.ams.appointment_service.dto.staffdto.UpdateDTO;
import com.ams.appointment_service.model.constant.StaffConfirmStatus;
import staff.AppointmentResponseDto;
import staff.EditAppointmentRequestDto;
import staff.GetAppointmentsResponse;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class GrpcStaffMapper {
    private static AppointmentResponseDto toDTO(AppointmentResponseDTO appointment) {
        return AppointmentResponseDto.newBuilder()
                .setId(String.valueOf(appointment.getId()))
                .setClientEmail(String.valueOf(appointment.getClientEmail()))
                .setClientName(String.valueOf(appointment.getClientName()))
                .setNotes(String.valueOf(appointment.getNotes()))
                .setStatus(String.valueOf(appointment.getStatus()))
                .setDate(String.valueOf(appointment.getDate()))
                .setStartTime(String.valueOf(appointment.getStartTime()))
                .setEndTime(String.valueOf(appointment.getEndTime()))
                .build();
    }

    public static GetAppointmentsResponse toDTo(List<AppointmentResponseDTO> appointments) {
        return GetAppointmentsResponse.newBuilder()
                .addAllAppointments(appointments.stream().map(GrpcStaffMapper::toDTO).toList())
                .build();
    }

    public static UpdateDTO toDTO(EditAppointmentRequestDto appointment) {
        UpdateDTO updateDTO = new UpdateDTO();
        updateDTO.setId(Long.parseLong(appointment.getId()));
        updateDTO.setNotes(appointment.getNotes());
        updateDTO.setStatus(StaffConfirmStatus.valueOf(appointment.getStatus()));
        updateDTO.setDate(LocalDate.parse(appointment.getDate()));
        updateDTO.setStartTime(LocalTime.parse(appointment.getStartTime()));
        updateDTO.setEndTime(LocalTime.parse(appointment.getEndTime()));
        return updateDTO;
    }

    public static AppointmentResponseDto toDTO(UpdateDTO updateDTO) {
        return AppointmentResponseDto.newBuilder()
                .setId(String.valueOf(updateDTO.getId()))
                .setNotes(String.valueOf(updateDTO.getNotes()))
                .setStatus(String.valueOf(updateDTO.getStatus()))
                .setDate(String.valueOf(updateDTO.getDate()))
                .setStartTime(String.valueOf(updateDTO.getStartTime()))
                .setEndTime(String.valueOf(updateDTO.getEndTime()))
                .build();
    }
}
