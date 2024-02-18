package com.kafka;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class Integration {

    private static final String BOOTSTRAP_SERVERS = "localhost:29092";
    private static final String TOPIC_NAME = "posts";

    public static void main(String[] args) {
        // Create topic on application startup
        createTopic();

        // Publish events
        publishEvents();

        // Consume events
        consumeEvents();
    }

    private static void createTopic() {
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);

        try (AdminClient adminClient = AdminClient.create(properties)) {
            NewTopic newTopic = new NewTopic(TOPIC_NAME, 1, (short) 1);
            adminClient.createTopics(Collections.singleton(newTopic));
            System.out.println("Topic '" + TOPIC_NAME + "' created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void publishEvents() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        try (Producer<String, String> producer = new KafkaProducer<>(properties)) {
            for (int i = 0; i < 10; i++) {
                String message = "Message " + i;
                producer.send(new ProducerRecord<>(TOPIC_NAME, message));
                System.out.println("Published message: " + message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void consumeEvents() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "group1");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        try (Consumer<String, String> consumer = new KafkaConsumer<>(properties)) {
            consumer.subscribe(Collections.singletonList(TOPIC_NAME));
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                records.forEach(record -> {
                    System.out.println("Received message: " + record.value());
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
