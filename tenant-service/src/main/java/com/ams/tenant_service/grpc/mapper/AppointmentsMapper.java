package com.ams.tenant_service.grpc.mapper;

import com.ams.tenant_service.model.constant.AppointmentRequestDTO;
import com.ams.tenant_service.model.constant.AppointmentResponseDTO;
import com.ams.tenant_service.model.constant.AppointmentStatus;
import staff.AppointmentResponseDto;
import staff.EditAppointmentRequestDto;
import staff.GetAppointmentsResponse;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AppointmentsMapper {

    public static List<AppointmentResponseDTO> toDTOs(GetAppointmentsResponse response) {
        return response.getAppointmentsList().stream()
                .map(AppointmentsMapper::toDTO).toList();
    }

    private static AppointmentResponseDTO toDTO(AppointmentResponseDto response) {
         AppointmentResponseDTO appointment = new AppointmentResponseDTO();
         appointment.setId(response.getId());
         appointment.setClientEmail(response.getClientEmail());
         appointment.setClientName(response.getClientName());
         appointment.setNotes(response.getNotes());
         appointment.setCustomerStatus(AppointmentStatus.valueOf(response.getCustomerStatus()));
         appointment.setStaffStatus(AppointmentStatus.valueOf(response.getStaffStatus()));
         appointment.setStartTime(LocalTime.parse(response.getStartTime()));
         appointment.setEndTime(LocalTime.parse(response.getEndTime()));
         appointment.setDate(LocalDate.parse(response.getDate()));
         return appointment;
    }

    public static EditAppointmentRequestDto toDTO(AppointmentRequestDTO request) {
        return EditAppointmentRequestDto.newBuilder()
                .setTenantId(request.tenantId)
                .setId(request.id)
                .setClientEmail(request.clientEmail)
                .setClientName(request.clientName)
                .setNotes(request.notes)
                .setStatus(request.staffStatus.toString())
                .setDate(request.date.toString())
                .setStartTime(request.startTime.toString())
                .setEndTime(request.endTime.toString())
                .build();
    }

}
