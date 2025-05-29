package com.ams.appointment_service.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.List;

@Configuration
public class KafkaConfiguration {

    @Bean
    public List<NewTopic> allTopics() {
        return List.of(
                TopicBuilder.name("notification").partitions(3).replicas(1).build()
        );
    }

}
