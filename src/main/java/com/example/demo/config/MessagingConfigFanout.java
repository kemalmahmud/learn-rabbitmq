package com.example.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfigFanout {

    public static final String QUEUE = "kemal_fanout_queue";
    public static final String QUEUE2 = "kemal_fanout2_queue";
    public static final String EXCHANGE = "kemal_fanout_exchange";

    @Bean
    public Queue fanoutQueue() {
        return new Queue(QUEUE);
    }
    @Bean
    public Queue fanoutQueue2() {
        return new Queue(QUEUE2);
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(EXCHANGE);
    }

    @Bean
    public Declarables fanoutExchangeBindings(
            FanoutExchange fanoutExchange,
            Queue fanoutQueue,
            Queue fanoutQueue2) {
        return new Declarables(
                BindingBuilder.bind(fanoutQueue).to(fanoutExchange),
                BindingBuilder.bind(fanoutQueue2).to(fanoutExchange)
        );
    }
}
