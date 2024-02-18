package com.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    private static final String TOPIC = "test";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        for (int i = 0; i < 10; i++) {
            kafkaTemplate.send(TOPIC, message + " " + i);
            System.out.println("Message sent: " + message + " " + i);
        }
    }
}
