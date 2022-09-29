package com.example.rabbitmqdemo.publisher;

import com.example.rabbitmqdemo.config.MessagingConfig;
import com.example.rabbitmqdemo.dto.Order;
import com.example.rabbitmqdemo.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order,@PathVariable String restaurantName){
        order.setOrderid((UUID.randomUUID().toString()));
        OrderStatus orderStatus = new OrderStatus(order,"Process","order placed done..."+restaurantName);
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE,MessagingConfig.ROUTINGKEY,orderStatus);
        return "Success..";
    }
}
