package com.lyd.spring.boot.amqp.producer;

import com.lyd.spring.boot.amqp.config.RabbitConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/9/30 ProjectName:spring-boot-cloud Version: 1.0
 */
@Component
@Slf4j
public class Sender {

    @Autowired
    private AmqpTemplate amqpTemplate;


    public void send(String message){
        log.info("call send message:{}",message);
        amqpTemplate.convertAndSend("annotationQueue",message);
    }

}
