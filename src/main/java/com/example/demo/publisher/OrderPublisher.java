package com.example.demo.publisher;

import com.example.demo.config.MessagingConfigDirect;
import com.example.demo.config.MessagingConfigFanout;
import com.example.demo.dto.Order;
import com.example.demo.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplateFanout;

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName) {
        order.setOrderId(UUID.randomUUID().toString());

        OrderStatus orderStatus = new OrderStatus(order, "PROCESS", "order placed sucessfully in " + restaurantName);
        /* publish status to queue yang terbind dengan exchange di routing key
           jadi method di bawah akan send ke queue kemal_test_queue karena
           queue tersebut yang ke bind dengan exchange kemal_test_exchange di routing key kemal_test_routingKey
         */
        rabbitTemplate.convertAndSend(MessagingConfigDirect.EXCHANGE, MessagingConfigDirect.ROUTING_KEY, orderStatus);
        //fanout
        orderStatus.setStatus("in fanout");
        rabbitTemplate.convertAndSend(MessagingConfigFanout.EXCHANGE, "", orderStatus);


        return "Success";
    }
}
