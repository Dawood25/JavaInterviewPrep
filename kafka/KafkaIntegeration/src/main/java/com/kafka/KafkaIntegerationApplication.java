package com.kafka;

import com.kafka.consumer.KafkaConsumer;
import com.kafka.producer.KafkaProducer;
import jakarta.annotation.PostConstruct;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
public class KafkaIntegerationApplication implements CommandLineRunner {
    @Autowired
    private KafkaProducer kafkaProducer;
    @Autowired
    private KafkaConsumer kafkaConsumer;

    public static void main(String[] args) {
        SpringApplication.run(KafkaIntegerationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        kafkaProducer.sendMessage("Hello, Kafka!");
    }


}
