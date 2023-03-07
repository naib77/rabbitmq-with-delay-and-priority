package com.hishab.rabbitmqdemo.controller;

import com.hishab.rabbitmqdemo.model.MessageRequest;
import com.hishab.rabbitmqdemo.services.ProduceMessageService;
import com.hishab.rabbitmqdemo.services.RabbitMQProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProduceMessageController {

    private ProduceMessageService produceMessageService;
    private RabbitMQProducer rabbitMQProducer;

    public ProduceMessageController(ProduceMessageService produceMessageService, RabbitMQProducer rabbitMQProducer) {
        this.produceMessageService = produceMessageService;
        this.rabbitMQProducer = rabbitMQProducer;
    }


    @PostMapping("/produce")
    public String produceMessage(@RequestBody MessageRequest request) {
        return rabbitMQProducer.sendMessageWithDelay(request.message, request.delayTime, request.priority);
//        return produceMessageService.produceMessage(message);
    }
}
