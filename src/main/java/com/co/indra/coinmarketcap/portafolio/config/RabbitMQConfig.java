package com.co.indra.coinmarketcap.portafolio.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue testQueueAlert() {
        return new Queue("g3-rabbit-cache");
    }
}
