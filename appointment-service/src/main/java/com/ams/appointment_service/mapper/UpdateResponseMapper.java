package com.ams.appointment_service.mapper;

import com.ams.appointment_service.dto.staffdto.UpdateDTO;
import com.ams.appointment_service.model.entities.Appointment;

public class UpdateResponseMapper {
    public static UpdateDTO toDTO(Appointment appointment) {
        UpdateDTO updateDTO = new UpdateDTO();
        appointment.setId(appointment.getId());
        appointment.setNotes(appointment.getNotes());
        appointment.setDate(appointment.getDate());
        appointment.setStartTime(appointment.getStartTime());
        appointment.setEndTime(appointment.getEndTime());
        appointment.setStatus(appointment.getStatus());
        return updateDTO;
    }
}
