package com.lyd.spring.boot.amqp.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/9/30 ProjectName:spring-boot-cloud Version: 1.0
 */
//@Configuration
public class RabbitConfiguration {

    public static final String TEST_QUEUE_NAME = "test";

    public static final String TEST_QUEUE_NAME1 = "test1";

    public static final String TEST_EXCHANGE_NAME = "testTopicExchange";


    @Bean
    public Queue testQueue(){
        return new Queue(TEST_QUEUE_NAME);
    }

    @Bean
    public Queue testQueue1(){
        return new Queue(TEST_QUEUE_NAME1);
    }

    @Bean
    public TopicExchange testTopicExchange(){
        return new TopicExchange(TEST_EXCHANGE_NAME);
    }

    @Bean
    public Binding testBinding(Exchange testTopicExchange,Queue testQueue){
        return BindingBuilder.bind(testQueue).to(testTopicExchange).with(TEST_QUEUE_NAME).noargs();
    }

}
