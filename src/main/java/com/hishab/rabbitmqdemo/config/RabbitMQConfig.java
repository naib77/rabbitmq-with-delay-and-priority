package com.hishab.rabbitmqdemo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.host}")
    private String rabbitMQHost;

    @Value("${spring.rabbitmq.port}")
    private int rabbitMQPort;

    @Value("${spring.rabbitmq.username}")
    private String rabbitMQUsername;

    @Value("${spring.rabbitmq.password}")
    private String rabbitMQPassword;
    @Value("${rabbitmq.queue.name}")
    private String queueName;
    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitMQHost, rabbitMQPort);
        connectionFactory.setUsername(rabbitMQUsername);
        connectionFactory.setPassword(rabbitMQPassword);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        return rabbitTemplate;
    }

//    @Bean
//    public DirectExchange exchange() {
//        return new DirectExchange(exchangeName);
//    }

//    @Bean
//    public Queue queue() {
//        return new Queue(queueName);
//    }

//    @Bean
//    public Binding binding() {
//        return BindingBuilder.bind(queue()).to(exchange()).with(routingKey);
//    }
//    @Bean
//    Queue createQueue() {
//        Map<String, Object> args= new HashMap<>();
//        args.put("x-max-priority", 10);
//        return new Queue("test02", false, false, false, args);
//    }

//    @Bean
//    TopicExchange exchange() {
//        Map<String, Object> args= new HashMap<>();
//        args.put("x-delayed-type", "direct");
////        args.put("type", "x-delayed-message");
//        return new TopicExchange("test-delayed-exchange-02",true,false,args);
//    }

//    @Bean
//    Binding binding(Queue queue, TopicExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with("myRoutingKey.#");
//    }

}

