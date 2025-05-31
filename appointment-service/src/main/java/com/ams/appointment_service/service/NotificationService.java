package com.ams.appointment_service.service;

import com.ams.appointment_service.kafka.KafkaProducer;
import com.ams.appointment_service.kafka.model.NotificationEventMapper;
import com.ams.appointment_service.model.constant.EventType;
import com.ams.appointment_service.model.entities.Appointment;
import com.ams.appointment_service.model.entities.StaffScheduleSnapshot;
import com.ams.appointment_service.multitenancy.schema.schema_resolver.TenantContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class NotificationService {

    private final KafkaProducer kafkaProducer;

    // APPOINTMENT_BOOKED: notify staff and customer
    public void sendAppointmentBookedNotification(Appointment appointment) {
        log.info("Tenant id: {}", TenantContext.INSTANCE.getCurrentTenant());
        StaffScheduleSnapshot staffSnapshot = appointment.getStaff();
        var event = NotificationEventMapper
                .toEvent(staffSnapshot.getName(), staffSnapshot.getName(), appointment, EventType.APPOINTMENT_BOOKED);
        kafkaProducer.sendEvent(event);
    }

    // APPOINTMENT_BOOKING_CONFIRMED: notify customer that their appointment was canceled
    public void notifyCustomerAppointmentBookingWasConfirmed(Appointment appointment) {
        StaffScheduleSnapshot staffSnapshot = appointment.getStaff();
        var event = NotificationEventMapper
                .toEvent(staffSnapshot.getName(), null, appointment, EventType.APPOINTMENT_BOOKING_CONFIRM);
        kafkaProducer.sendEvent(event);
    }

    // APPOINTMENT_CANCELED_BY_CUSTOMER: notify staff that an appointment was canceled
    public void notifyStaffAppointmentHasBeenCancelled(Appointment appointment) {
        StaffScheduleSnapshot staffSnapshot = appointment.getStaff();
        var event = NotificationEventMapper
                .toEvent(staffSnapshot.getName(), staffSnapshot.getEmail(), appointment, EventType.APPOINTMENT_CANCELED_BY_CUSTOMER);
        kafkaProducer.sendEvent(event);
    }

    // APPOINTMENT_CANCELLED_BY_STAFF: notify customer that their appointment was canceled
    public void notifyCustomerAppointmentHasBeenCancelled(Appointment appointment) {
        var event = NotificationEventMapper
                .toEvent(null, null, appointment, EventType.APPOINTMENT_CANCELED_BY_STAFF);
        kafkaProducer.sendEvent(event);
    }

    // APPOINTMENT_RESCHEDULED_BY_CUSTOMER: notify staff that an appointment was rescheduled; so they can confirm
    public void notifyStaffAppointmentWasRescheduled(Appointment appointment) {
        StaffScheduleSnapshot staffSnapshot = appointment.getStaff();
        var event = NotificationEventMapper
                .toEvent(staffSnapshot.getName(), staffSnapshot.getEmail(), appointment, EventType.APPOINTMENT_RESCHEDULED_BY_CUSTOMER);
        kafkaProducer.sendEvent(event);
    }

    // APPOINTMENT_RESCHEDULED_BY_STAFF: notify customer that their appointment was rescheduled; so they can confirm
    public void notifyCustomerAppointmentWasRescheduled(Appointment appointment) {
        StaffScheduleSnapshot staffSnapshot = appointment.getStaff();
        var event = NotificationEventMapper
                .toEvent(staffSnapshot.getName(), null, appointment, EventType.APPOINTMENT_RESCHEDULED_BY_STAFF);
        kafkaProducer.sendEvent(event);
    }

    // APPOINTMENT_RESCHEDULED_CONFIRMED_BY_CUSTOMER: Notify staff
    public void notifyStaffReschedulingWasComFirm(Appointment appointment) {
        StaffScheduleSnapshot staffSnapshot = appointment.getStaff();
        var event = NotificationEventMapper
            .toEvent(staffSnapshot.getName(), staffSnapshot.getEmail(), appointment,
                    EventType.APPOINTMENT_RESCHEDULED_CONFIRMED_BY_CUSTOMER);
        kafkaProducer.sendEvent(event);
    }

    // APPOINTMENT_RESCHEDULED_CONFIRMED_BY_STAFF: Notify staff
    public void notifyCustomerReschedulingWasComFirm(Appointment appointment) {
        StaffScheduleSnapshot staffSnapshot = appointment.getStaff();
        var event = NotificationEventMapper
                .toEvent(staffSnapshot.getName(), null, appointment,
                        EventType.APPOINTMENT_RESCHEDULED_CONFIRMED_BY_STAFF);
        kafkaProducer.sendEvent(event);
    }

}
