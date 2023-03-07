package com.hishab.rabbitmqdemo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageConsumer {
    @RabbitListener(queues = "test02")
    public void receiveMessage(Message message) throws InterruptedException {
        log.info("Received message: " + new String(message.getBody()));
        Thread.sleep(5000); // simulate processing time
//        log.info("Processed message: " + new String(message.getBody()));
    }
}
