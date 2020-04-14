package com.example.kafka_demo.producer;

import com.example.kafka_demo.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("msg")
public class MsgController {
    private static final Logger log = LoggerFactory.getLogger(MsgController.class);

    @Autowired
    private KafkaTemplate<String, UserDto> kafkaTemplate;

    @PostMapping
    public void sendOrder(@RequestBody UserDto msg){
        String msgId = UUID.randomUUID().toString();
        log.info("Prodused message. Id: {}, body: {}", msgId, msg);
        ListenableFuture<SendResult<String, UserDto>> result = kafkaTemplate.send("msg", msgId, msg);
        result.addCallback(s-> log.info(s.toString()),
                e-> log.warn(e.getMessage()));
        kafkaTemplate.flush();
    }
}
