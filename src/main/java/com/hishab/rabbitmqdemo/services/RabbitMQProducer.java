package com.hishab.rabbitmqdemo.services;


import com.hishab.rabbitmqdemo.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQProducer {
    private RabbitTemplate rabbitTemplate;
    private RabbitMQConfig rabbitMQConfig;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate, RabbitMQConfig rabbitMQConfig) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMQConfig = rabbitMQConfig;
    }

    public String sendMessageWithDelay(String message, int delayTime, int priority) {
        MessageProperties props = new MessageProperties();
//        props.setHeader("x-delay", delayTime);
        props.setDelay(delayTime);
        props.setPriority(priority);
        props.setHeader("x-delayed-type","direct");
        props.setHeader("x-delayed-message",true);
        Message msg = new Message(message.getBytes(), props);
//        rabbitTemplate.convertAndSend("test-delayed-exchange-02", "myRoutingKey.#", msg);
        rabbitTemplate.convertAndSend("test-delayed-exchange-02",
                "myRoutingKey.#", msg, message1 -> {
            message1.getMessageProperties().setPriority(priority);
            return message1;
        });
        log.info("Message(" + message + ")" + " has been produced.");
        return "Message(" + message + ")" + " has been produced.";
    }
}
