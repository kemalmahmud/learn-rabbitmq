package com.example.demo.consumer;

import com.example.demo.config.MessagingConfigDirect;
import com.example.demo.config.MessagingConfigFanout;
import com.example.demo.config.MessagingConfigHeader;
import com.example.demo.config.MessagingConfigTopic;
import com.example.demo.dto.OrderStatus;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {
    @RabbitListener(queues = MessagingConfigDirect.QUEUE)
    public void consumeMessageFromQueue(OrderStatus orderStatus) {
        System.out.println("Message from queue: " + orderStatus);
    }

    @RabbitListener(queues = MessagingConfigFanout.QUEUE)
    public void consumeMessageFromQueueFanout(OrderStatus orderStatus) {
        System.out.println("Message from queue fanout: " + orderStatus);
    }

    @RabbitListener(queues = MessagingConfigFanout.QUEUE2)
    public void consumeMessageFromQueueFanout2(OrderStatus orderStatus) {
        System.out.println("Message from queue fanout 2: " + orderStatus);
    }

    @RabbitListener(queues = MessagingConfigTopic.QUEUE)
    public void consumeMessageFromQueueTopic(OrderStatus orderStatus) {
        System.out.println("Message from queue topic 1: " + orderStatus);
    }

    @RabbitListener(queues = MessagingConfigTopic.QUEUE2)
    public void consumeMessageFromQueueTopic2(OrderStatus orderStatus) {
        System.out.println("Message from queue topic 2: " + orderStatus);
    }

    @RabbitListener(queues = MessagingConfigHeader.QUEUE)
    public void consumeMessageFromHeaderTopic(Message message) {
        System.out.println("Message from queue header 1: " + message.getMessageProperties());
    }

    @RabbitListener(queues = MessagingConfigHeader.QUEUE2)
    public void consumeMessageFromHeaderTopic2(Message message) {
        System.out.println("Message from queue header 2: " + message.getMessageProperties());
    }
}
