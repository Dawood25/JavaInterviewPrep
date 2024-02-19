package com.kafka.producer;

import com.kafka.payload.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JsonKafkaProducer {
    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    public void sendMessage(User data) {
        Message<User> message = MessageBuilder.withPayload(data).setHeader(KafkaHeaders.TOPIC, "test2").setHeader("msgType", "Json").build();
        kafkaTemplate.send(message);
        log.info(String.format("Message sent -> %s", data.toString()));
    }
}
