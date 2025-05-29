package com.ams.appointment_service.service;

import com.ams.appointment_service.model.entities.Appointment;
import com.ams.appointment_service.repository.AppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaService {

    private final AppointmentRepository appointmentRepository;

    // Send APPOINTMENT_RESCHEDULED to the notification service to notify the staff & Client
    public void sendAppointmentBookedNotification(Appointment appointment) {
        /*
        grpc functions:
           * getStaffDetails(staffId) returns String email, String name
           * notifyStaffAndCustomer(staffEmail, staffName, customerEmail, customerName, date, endTime, startTime, notes) return void
        */
        System.out.println("AppointmentService -> sendAppointmentBookedNotification: Appointment");
    }

    // Send APPOINTMENT_RESCHEDULED to notification service
    public void sendRescheduleRequestNotification(Appointment appointment) {
        /*
        grpc functions:
           * notifyStaff(staffEmail, customerName, date, endTime, startTime, notes) return void
        */
        System.out.println("AppointmentService -> sendRescheduleRequestNotification: Appointment");
    }

    // Send APPOINTMENT_RESCHEDULED_CONFIRMED event to notification service
    public void sendRescheduleConfirmationNotification(Appointment appointment) {
        /*
        grpc functions:
           * notifyCustomer(staffName, customerEmail, customerName, date, endTime, startTime, notes) return void
        */
        System.out.println("AppointmentService -> sendRescheduleConfirmationNotification: Appointment");
    }

    // Notify staff about cancellation
    public void notifyStaffAppointmentCancelled(Appointment appointment) {
        /*
        grpc functions:
           * notifyStaffAppointmentCancelled(staffEmail, customerName, date, endTime, startTime, notes) return void
        */
        System.out.println("AppointmentService -> notifyStaffAppointmentCancelled: Appointment");
    }
}
