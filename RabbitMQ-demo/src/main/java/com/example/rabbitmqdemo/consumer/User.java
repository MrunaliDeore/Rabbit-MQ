package com.example.rabbitmqdemo.consumer;

import com.example.rabbitmqdemo.config.MessagingConfig;
import com.example.rabbitmqdemo.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {
    @RabbitListener(queues = MessagingConfig.QUEUE)
    public  void consumeMessageFromQueue(OrderStatus orderStatus){
        System.out.println("Message recieved from queue :"+orderStatus);
    }
}
