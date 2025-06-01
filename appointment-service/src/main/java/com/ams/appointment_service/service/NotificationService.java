package com.ams.appointment_service.service;

import com.ams.appointment_service.kafka.KafkaProducer;
import com.ams.appointment_service.kafka.model.NotificationEventMapper;
import com.ams.appointment_service.model.constant.EventType;
import com.ams.appointment_service.model.entities.Appointment;
import com.ams.appointment_service.model.entities.StaffScheduleSnapshot;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class NotificationService {

    private final KafkaProducer kafkaProducer;

    public void sendAppointmentBookedNotification(Appointment appointment) {
        StaffScheduleSnapshot staffSnapshot = appointment.getStaff();
        var event = NotificationEventMapper
                .toEvent(staffSnapshot.getName(), staffSnapshot.getEmail(), appointment, EventType.APPOINTMENT_BOOKED);
        kafkaProducer.sendEvent(event);
    }

    public void notifyCustomerAppointmentBookingWasConfirmed(Appointment appointment) {
        StaffScheduleSnapshot staffSnapshot = appointment.getStaff();
        var event = NotificationEventMapper
                .toEvent(staffSnapshot.getName(), null, appointment, EventType.APPOINTMENT_BOOKING_CONFIRM);
        kafkaProducer.sendEvent(event);
    }

    public void notifyStaffAppointmentHasBeenCancelled(Appointment appointment) {
        StaffScheduleSnapshot staffSnapshot = appointment.getStaff();
        var event = NotificationEventMapper
                .toEvent(staffSnapshot.getName(), staffSnapshot.getEmail(), appointment, EventType.APPOINTMENT_CANCELED_BY_CUSTOMER);
        kafkaProducer.sendEvent(event);
    }

    public void notifyCustomerAppointmentHasBeenCancelled(Appointment appointment) {
        var event = NotificationEventMapper
                .toEvent(null, null, appointment, EventType.APPOINTMENT_CANCELED_BY_STAFF);
        kafkaProducer.sendEvent(event);
    }

    public void notifyStaffAppointmentWasRescheduled(Appointment appointment) {
        StaffScheduleSnapshot staffSnapshot = appointment.getStaff();
        var event = NotificationEventMapper
                .toEvent(staffSnapshot.getName(), staffSnapshot.getEmail(), appointment, EventType.APPOINTMENT_RESCHEDULED_BY_CUSTOMER);
        kafkaProducer.sendEvent(event);
    }

    public void notifyCustomerAppointmentWasRescheduled(Appointment appointment) {
        StaffScheduleSnapshot staffSnapshot = appointment.getStaff();
        var event = NotificationEventMapper
                .toEvent(staffSnapshot.getName(), null, appointment, EventType.APPOINTMENT_RESCHEDULED_BY_STAFF);
        kafkaProducer.sendEvent(event);
    }

    public void notifyStaffReschedulingWasComFirm(Appointment appointment) {
        StaffScheduleSnapshot staffSnapshot = appointment.getStaff();
        var event = NotificationEventMapper
            .toEvent(staffSnapshot.getName(), staffSnapshot.getEmail(), appointment,
                    EventType.APPOINTMENT_RESCHEDULED_CONFIRMED_BY_CUSTOMER);
        kafkaProducer.sendEvent(event);
    }

    public void notifyCustomerReschedulingWasComFirm(Appointment appointment) {
        StaffScheduleSnapshot staffSnapshot = appointment.getStaff();
        var event = NotificationEventMapper
                .toEvent(staffSnapshot.getName(), null, appointment,
                        EventType.APPOINTMENT_RESCHEDULED_CONFIRMED_BY_STAFF);
        kafkaProducer.sendEvent(event);
    }

}
