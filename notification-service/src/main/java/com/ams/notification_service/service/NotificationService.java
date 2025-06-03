package com.ams.notification_service.service;

import com.ams.notification_service.constant.EmailType;
import com.ams.notification_service.grpc.client.TenantServiceGrpcClient;
import com.google.type.Date;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import notification.event.Appointment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import tenant.Tenant;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class NotificationService {

    private final TenantServiceGrpcClient client;
    private final EmailService emailService;
    private final ResourceLoader resourceLoader;

    public void handleNotificationEvent(Appointment appointment) {
        try {
            Tenant tenant = client.getTenantDetails(appointment.getTenantId());

            Map<String, String> data = TemplateRender.mapData(tenant, appointment);
            String type = appointment.getEventType();
            String layout = tenant.getNotificationPreferences().getTemplatesMap().get("layout");
            String content = tenant.getNotificationPreferences().getTemplatesMap().get("type");

            String renderContent = TemplateRender.renderTemplate(layout, content, data, type, resourceLoader);
            String receiverEmail = TemplateRender.getReceiverEmail(data, type);
            String subject = TemplateRender.getSubject(type);
            emailService.sendMail(receiverEmail, subject, renderContent);
        } catch (Exception e) {
            log.info("Error parsing a notification evet {}", e.getMessage());
        }

    }

}

class TemplateRender {

    public static String renderTemplate(
            String layout, String content, Map<String, String> data, String emailType, ResourceLoader resourceLoader
    ) {
        String emailContent = content; String emailLayout = layout;
        if (emailType != null || content.isBlank() || layout.isBlank()) {
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
                Resource resource = resourceLoader.getResource("classpath:" + localTemplate);
                if (content == null || content.isBlank())  emailContent = Files.readString(resource.getFile().toPath());
                if (layout == null || layout.isBlank()) emailLayout = Files
                            .readString(resourceLoader.getResource("classpath:templates/emails/layout.html").getFile().toPath());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return render(emailLayout, emailContent, data);
    }

    private static String render(String layout, String content, Map<String, String> data) {
        String renderedContent = content;
        for (Map.Entry<String, String> entry: data.entrySet()) {
            renderedContent = renderedContent.replace(entry.getKey(), entry.getValue());
        }

        String finalEmail = layout.replace("{{bodyContent}}", renderedContent);
        for (Map.Entry<String, String> entry: data.entrySet()) {
            finalEmail = finalEmail.replace(entry.getKey(), entry.getValue());
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
        data.put("{{primaryColor}}", tenant.getPrimaryColor());

        data.put("{{staffName}}", appointment.getStaffName());
        data.put("{{staffEmail}}", appointment.getStaffEmail());
        data.put("{{staffStatus}}", appointment.getStaffStatus());

        data.put("{{customerName}}", appointment.getCustomerName());
        data.put("{{customerEmail}}", appointment.getCustomerEmail());
        data.put("{{customerStatus}}", appointment.getCustomerStaus());

        Date date = appointment.getDate();
        data.put("{{date}}", formatWithOrdinal(LocalDate.of(date.getYear(), date.getMonth(), date.getDay())));
        data.put("{{startTime}}", appointment.getStartTime());
        data.put("{{endTime}}", appointment.getEndTime());
        data.put("{{note}}", appointment.getNote());
        data.put("{{year}}", String.valueOf(LocalDate.now().getYear()));
        return data;
    }

    private static String formatWithOrdinal(LocalDate date) {
        int day = date.getDayOfMonth();
        String suffix = getDaySuffix(day);
        return date.format(DateTimeFormatter
                .ofPattern("MMMM")) + " " + day + suffix + " " + date.
                format(DateTimeFormatter.ofPattern("yyyy"));
    }

    private static String getDaySuffix(int day) {
        if (day >= 11 && day <= 13) {
            return "th";
        }
        return switch (day % 10) {
            case 1 -> "st";
            case 2 -> "nd";
            case 3 -> "rd";
            default -> "th";
        };
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