package com.ams.appointment_service.kafka.model;

import com.ams.appointment_service.model.constant.EventType;
import com.google.type.Date;
import notification.event.Appointment;

public class NotificationEventMapper {

    public static Appointment toEvent(
            String staffName, String staffEmail,
            com.ams.appointment_service.model.entities.Appointment appointment,
            EventType eventType
    ) {
        return Appointment.newBuilder()
                .setStaffEmail(staffEmail != null ? staffEmail : "")
                .setStaffName(staffName != null ? staffName : "")
                .setCustomerEmail(appointment.getCustomerEmail())
                .setCustomerName(appointment.getCustomerName())
                .setCustomerStaus(appointment.getCustomerStatus().toString())
                .setStaffStatus(appointment.getStaffStatus().toString())
                .setNote(appointment.getNotes())
                .setDate(Date.newBuilder()
                        .setYear(appointment.getDate().getYear())
                        .setMonth(appointment.getDate().getMonthValue())
                        .setMonth(appointment.getDate().getMonthValue())
                        .setDay(appointment.getDate().getDayOfMonth())
                        .build())
                .setStartTime(appointment.getStartTime().toString())
                .setEndTime(appointment.getEndTime().toString())
                .setEventType(eventType.toString())
                .buildPartial();
    }
}

