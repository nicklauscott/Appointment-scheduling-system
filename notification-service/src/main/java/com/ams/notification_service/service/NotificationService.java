package com.ams.notification_service.service;

import com.ams.notification_service.constant.EmailType;
import com.ams.notification_service.grpc.client.TenantServiceGrpcClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import notification.event.Appointment;
import org.springframework.stereotype.Service;
import tenant.Tenant;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class NotificationService {

    private final TenantServiceGrpcClient client;
    private final EmailService emailService;

    public void handleNotificationEvent(Appointment appointment) {
        try {
            Tenant tenant = client.getTenantDetails(appointment.getTenantId());

            Map<String, String> data = TemplateRender.mapData(tenant, appointment);
            String type = appointment.getEventType();
            String layout = tenant.getNotificationPreferences().getTemplatesMap().get("layout");
            String content = tenant.getNotificationPreferences().getTemplatesMap().get("type");

            String renderContent = TemplateRender.renderTemplate(layout, content, data, type);
            String receiverEmail = TemplateRender.getReceiverEmail(data, type);
            String subject = TemplateRender.getSubject(type);
            emailService.sendMail(receiverEmail, subject, renderContent);
        } catch (Exception e) {
            log.info("Error parsing a notification evet {}", e.getMessage());
        }

    }

}

class TemplateRender {

    public static String renderTemplate(String layout, String content, Map<String, String> data, String emailType) {
        if (emailType != null) {
            try {
                var type = EmailType.valueOf(emailType);
                var localTemplate = switch (type) {
                    case APPOINTMENT_BOOKED -> "templates/emails/staff_new_appointment_booked_by_client.html";
                    case APPOINTMENT_BOOKING_CONFIRM -> "templates/emails/client_appointment_confirmed.html";
                    case APPOINTMENT_CANCELED_BY_STAFF -> "templates/emails/client_appointment_canceled.html";
                    case APPOINTMENT_CANCELED_BY_CUSTOMER -> "templates/emails/staff_appointment_canceled_by_client.html";
                    case APPOINTMENT_RESCHEDULED_BY_STAFF -> "templates/emails/client_appointment_rescheduled.html";
                    case APPOINTMENT_RESCHEDULED_BY_CUSTOMER -> "templates/emails/staff_appointment_rescheduled_by_client.html";
                    case APPOINTMENT_RESCHEDULED_CONFIRMED_BY_STAFF -> "templates/emails/client_rescheduled_appointment_confirmed_by_staff.html";
                    case APPOINTMENT_RESCHEDULED_CONFIRMED_BY_CUSTOMER -> "templates/emails/staff_rescheduled_appointment_confirmed_by_client";
                };
                String localLayout = Files.readString(Path.of(localTemplate));
                String localContent = Files.readString(Path.of("templates/emails/layout.html"));
                return render(localLayout, localContent, data);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return render(layout, content, data);
    }

    private static String render(String layout, String content, Map<String, String> data) {
        String renderedContent = content;
        for (Map.Entry<String, String> entry: data.entrySet()) {
            renderedContent = renderedContent.replace("{{"+entry.getKey()+"}}", entry.getValue());
        }

        String finalEmail = layout.replace("{{bodyContent}}", renderedContent);
        for (Map.Entry<String, String> entry: data.entrySet()) {
            finalEmail = finalEmail.replace("{{"+entry.getKey()+"}}", entry.getValue());
        }

        return finalEmail;
    }

    public static Map<String, String> mapData(Tenant tenant, Appointment appointment) {
        Map<String, String> data = new HashMap<>();
        data.put("{{companyName}}", tenant.getCompanyName());
        data.put("{{logoUrl}}", tenant.getLogoUrl());
        data.put("{{domain}}", tenant.getDomain());
        data.put("{{email}}", tenant.getContactInfo().getEmail());
        data.put("{{phone}}", tenant.getContactInfo().getPhone());
        data.put("{{address}}", tenant.getContactInfo().getAddress());

        data.put("{{staffName}}", appointment.getStaffName());
        data.put("{{staffEmail}}", appointment.getStaffEmail());
        data.put("{{staffStatus}}", appointment.getStaffStatus());

        data.put("{{customerName}}", appointment.getStaffStatus());
        data.put("{{customerEmail}}", appointment.getStaffStatus());
        data.put("{{customerStatus}}", appointment.getStaffStatus());

        data.put("{{date}}", appointment.getStaffStatus());
        data.put("{{startTime}}", appointment.getStaffStatus());
        data.put("{{endTime}}", appointment.getStaffStatus());
        data.put("{{note}}", appointment.getStaffStatus());
        return data;
    }

    public static String getReceiverEmail(Map<String, String> data, String emailType) {
        return switch (EmailType.valueOf(emailType)) {
            case APPOINTMENT_BOOKED, APPOINTMENT_CANCELED_BY_CUSTOMER, APPOINTMENT_RESCHEDULED_BY_CUSTOMER,
                 APPOINTMENT_RESCHEDULED_CONFIRMED_BY_CUSTOMER -> data.get("{{staffEmail}}");
            case APPOINTMENT_BOOKING_CONFIRM, APPOINTMENT_RESCHEDULED_CONFIRMED_BY_STAFF,
                 APPOINTMENT_CANCELED_BY_STAFF, APPOINTMENT_RESCHEDULED_BY_STAFF -> data.get("{{customerEmail}}");
        };
    }

    public static String getSubject(String emailType) {
        return switch (EmailType.valueOf(emailType)) {
            case APPOINTMENT_BOOKED -> "New Appointment Booked by Client";
            case APPOINTMENT_BOOKING_CONFIRM -> "Appointment Confirmed";
            case APPOINTMENT_CANCELED_BY_STAFF -> "Appointment Canceled";
            case APPOINTMENT_CANCELED_BY_CUSTOMER -> "Appointment Canceled by Customer";
            case APPOINTMENT_RESCHEDULED_BY_STAFF -> "Appointment Rescheduled";
            case APPOINTMENT_RESCHEDULED_BY_CUSTOMER -> "Appointment Rescheduled by Customer";
            case APPOINTMENT_RESCHEDULED_CONFIRMED_BY_STAFF -> "Rescheduled Appointment Confirmed by Staff";
            case APPOINTMENT_RESCHEDULED_CONFIRMED_BY_CUSTOMER -> "Rescheduled Appointment Confirmed by Customer";
        };
    }

}




/*

Tenant:
{{companyName}}, {{logoUrl}}, {{domain}}, {{email}}, {{phone}}, {{address}}

Client:
{{customerName}}, {{customerEmail}}, {{customerStatus}}, {{date}}, {{startTime}}, {{endTime}}, {{note}}

Staff:
{{staffName}}, {{staffEmail}}, {{staffStatus}}



    string staff_email = 1;
    string staff_name = 2;

    string customer_email = 3;
    string customer_name = 4;

    string customer_staus = 5;
    string staff_status = 6;

    google.type.Date date = 7;
    string start_time = 8;
    string end_time = 9;
    string note = 10;

    string tenant_id = 11;
    string event_type = 12;
 */

/*

client_appointment_confirmed.html
client_appointment_canceled.html
client_appointment_rescheduled.html
staff_appointment_canceled_by_client.html
staff_appointment_rescheduled_by_client.html
staff_new_appointment_booked_by_client.html


  Tenant info
     company_name
     logo_url
     domain
     email
     phone
     address

  Client info
     staff_name
     customer_email
     customer_name
     customer_staus
     staff_status
     date
     start_time
     end_time
     note

  Staff info
    email
    name

 "With these fields, generate these email template
  1.notify client the appointment the book has been confirmed.
  2.notify client the appointment the book has been rescheduled, they should open their app and accept or decline it.
  3.notify client the appointment the book has been canceled.

  4. notify a staff that a client booked an appointment with them, they should open their app and accept or decline it.
  5. notify a staff that a client has rescheduled an appointment with them, they should open their app and accept or decline it.
  5. notify a staff that a client has canceled an appointment with them.
 "



message Tenant {
    string tenant_id = 1;
    string company_name = 2;
    string logo_url = 3;
    string primary_color = 4;

    NotificationPreferences notification_preferences = 5;
    string domain = 6;
    ContactInfo contact_info = 7;
    map<string, string> custom_config = 8;
}

message NotificationPreferences {
    string sender_email = 1;
    map<string, string> templates = 2;
}

message ContactInfo {
    string email = 1;
    string phone = 2;
    string address = 3;
}
 */
