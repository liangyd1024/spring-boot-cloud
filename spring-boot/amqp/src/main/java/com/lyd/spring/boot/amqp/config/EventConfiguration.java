package com.lyd.spring.boot.amqp.config;

import com.lyd.spring.boot.amqp.event.listener.MessageListener;
import com.lyd.spring.boot.amqp.event.publisher.MessagePublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by aaa on 2017/10/4.
 */
@Configuration
public class EventConfiguration {

    @Bean
    public ApplicationListener messageListener(){
        return new MessageListener();
    }

    @Bean
    public ApplicationEventPublisher messagePublisher(){
        return new MessagePublisher();
    }

}
