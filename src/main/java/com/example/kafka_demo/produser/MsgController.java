package com.example.kafka_demo.produser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("msg")
public class MsgController {
    private static final Logger log = LoggerFactory.getLogger(MsgController.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping
    public void sendOrder(String msgId, String msgBody){
        log.info("Prodused message. Id: {}, body: {}", msgId, msgBody);
        kafkaTemplate.send("msg", msgId, msgBody);
    }
}
