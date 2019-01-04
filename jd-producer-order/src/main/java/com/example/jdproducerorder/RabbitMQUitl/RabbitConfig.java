package com.example.jdproducerorder.RabbitMQUitl;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String QUEUE_CREATE = "QUEUE_CREATE";



    @Bean
    public Queue queue(){
        return new Queue(QUEUE_CREATE,true,false,false);
    }

}
