package com.cgwx.ConfigurationSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue RabbitMQ() {
        return new Queue("publishOrder");

    }
}
