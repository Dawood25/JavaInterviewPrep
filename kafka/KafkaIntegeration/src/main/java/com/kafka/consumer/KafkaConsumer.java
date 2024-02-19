package com.kafka.consumer;

import com.kafka.payload.User;
import com.kafka.producer.KafkaProducer;
import jakarta.annotation.PostConstruct;
import org.apache.kafka.common.header.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
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
    @KafkaListener(topics = "test2",groupId = "myGroup")
    public void consumeJsonMessage(@Payload User user, @Header (name="msgType") String value) {
        System.out.println(value);
        System.out.println(user.toString());
    }

}
