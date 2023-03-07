package com.hishab.rabbitmqdemo.model;

import lombok.Data;

@Data
public class MessageRequest {
    public String message;
    public int delayTime;
    public int priority;

}
