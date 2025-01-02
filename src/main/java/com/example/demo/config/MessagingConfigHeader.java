package com.example.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class MessagingConfigHeader {

    public static final String QUEUE = "kemal_header_queue";
    public static final String QUEUE2 = "kemal_header_queue_2";
    public static final String EXCHANGE = "kemal_header_exchange";

    @Bean
    public Queue headerQueue() {
        return new Queue(QUEUE);
    }
    @Bean
    public Queue headerQueue2() {
        return new Queue(QUEUE2);
    }

    @Bean
    public HeadersExchange headersExchange(){
        return new HeadersExchange(EXCHANGE);
    }

    @Bean
    public Declarables headerExhangeBindings(Queue headerQueue, Queue headerQueue2, HeadersExchange headersExchange) {
        return new Declarables(
                BindingBuilder.bind(headerQueue).to(headersExchange).whereAll(Map.of("from", "kemal", "to", "you")).match(),
                BindingBuilder.bind(headerQueue2).to(headersExchange).whereAny(Map.of("from", "kemal", "to", "you")).match()
//        return BindingBuilder.bind(queue).to(exchange).where("testId").exists(); //contoh lain
        );
    }
}
