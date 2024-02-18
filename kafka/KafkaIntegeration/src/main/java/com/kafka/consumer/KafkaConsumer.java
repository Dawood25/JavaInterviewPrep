package com.kafka.consumer;

import com.kafka.producer.KafkaProducer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @Autowired
    KafkaProducer kafkaProducer;

    @KafkaListener(topics = "test", groupId = "myGroup")
    public void consume(String message) {

        System.out.println("Consumed message: " + message);
        // Process the consumed message
    }

}
