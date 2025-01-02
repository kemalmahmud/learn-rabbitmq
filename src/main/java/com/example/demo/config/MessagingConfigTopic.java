package com.example.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfigTopic {

    public static final String QUEUE = "kemalQueue1";
    public static final String QUEUE2 = "kemalQueue2";
    public static final String EXCHANGE = "kemal_topic_exchange";
    public static final String ROUTING_KEY_PATTERN = "#.kemal.*";
    public static final String ROUTING_KEY_PATTERN2 = "*.kemal.#";
    public static final String ROUTING_KEY2 = "kemal.sport";
    public static final String ROUTING_KEY3 = "test.a.kemal"; // gak bakal kekirim kemana2
    public static final String ROUTING_KEY4 = "test.kemal.xyz.qrs";

    @Bean
    public Queue topicQueue() {
        return new Queue(QUEUE);
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue(QUEUE2);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Declarables topicExchangeBindings(
            TopicExchange topicExchange,
            Queue topicQueue,
            Queue topicQueue2) {
        return new Declarables(
                BindingBuilder.bind(topicQueue).to(topicExchange).with(ROUTING_KEY_PATTERN),
                BindingBuilder.bind(topicQueue2).to(topicExchange).with(ROUTING_KEY_PATTERN2)
        );
    }
}
