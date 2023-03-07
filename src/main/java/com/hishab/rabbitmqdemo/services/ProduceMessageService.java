package com.hishab.rabbitmqdemo.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProduceMessageService {

    private final RabbitTemplate rabbitTemplate;

    public ProduceMessageService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public String produceMessage(String message) {
        rabbitTemplate.convertAndSend("test", "1234",
                message);
        return "Message(" + message + ")" + " has been produced.";
    }
}
