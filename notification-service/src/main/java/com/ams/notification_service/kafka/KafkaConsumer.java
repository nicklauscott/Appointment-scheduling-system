package com.ams.notification_service.kafka;

import com.ams.notification_service.service.NotificationService;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.AllArgsConstructor;
import notification.event.Appointment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);
    private final NotificationService service;

    @KafkaListener(topics = "notification", groupId = "notification_service")
    public void consumeEvent(byte[] event) {
        try {
            Appointment appointment = Appointment.parseFrom(event);
            log.info("Received GRPC Appointment event: {}", appointment.toString());
            log.info("Received GRPC Appointment event with tenant id: {}", appointment.getTenantId());
            service.handleNotificationEvent(appointment);
        } catch (InvalidProtocolBufferException e) {
            log.info("Error deserializing {} event", e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
