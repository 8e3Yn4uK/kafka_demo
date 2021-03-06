package com.example.kafka_demo.consumer;

import com.example.kafka_demo.dto.UserDto;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@EnableKafka
@SpringBootApplication
public class SimpleKafkaExampleApplication {
    private static final Logger log = LoggerFactory.getLogger(SimpleKafkaExampleApplication.class);

    @KafkaListener(topics="msg")
    public void msgListener(ConsumerRecord<String, UserDto> record){
        log.info("Partition: {}", record.partition());
        log.info("Key: {}", record.key());
        log.info("Value: {}", record.value());
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleKafkaExampleApplication.class, args);
    }

}
