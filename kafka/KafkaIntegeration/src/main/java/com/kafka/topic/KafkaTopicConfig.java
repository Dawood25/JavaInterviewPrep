package com.kafka.topic;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class KafkaTopicConfig {

    private final int numPartitions = 3; // Adjust as needed


    @Bean
    public NewTopic testTopic() {
        return new NewTopic("test", numPartitions, (short) 1); // Change topic name and replication factor as needed
    }
}
