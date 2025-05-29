package com.ams.appointment_service.mapper;

import com.ams.appointment_service.dto.AppointmentResponseDTO;
import com.ams.appointment_service.model.entities.Appointment;

public class AppointmentMapper {

    public static AppointmentResponseDTO toDTO(Appointment appointment) {
        AppointmentResponseDTO appointmentResponseDTO = new AppointmentResponseDTO();
        appointmentResponseDTO.setId(appointment.getId());
        appointmentResponseDTO.setClientName(appointment.getCustomerName());
        appointmentResponseDTO.setClientEmail(appointment.getCustomerEmail());
        appointmentResponseDTO.setNotes(appointment.getNotes());
        appointmentResponseDTO.setDate(appointment.getDate());
        appointmentResponseDTO.setStartTime(appointment.getStartTime());
        appointmentResponseDTO.setEndTime(appointment.getEndTime());
        appointmentResponseDTO.setStatus(appointment.getStatus());
        return appointmentResponseDTO;
    }
}
