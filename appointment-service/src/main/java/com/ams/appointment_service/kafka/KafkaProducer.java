package com.ams.appointment_service.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import notification.event.Appointment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public void sendEvent(Appointment event) {
        try {
            kafkaTemplate.send("notification", event.toByteArray());
        } catch (Exception e) {
            log.info("Error occurred while sending an {} event. {}", event.getEventType(), e.getMessage());
            throw e;
        }
    }

}
