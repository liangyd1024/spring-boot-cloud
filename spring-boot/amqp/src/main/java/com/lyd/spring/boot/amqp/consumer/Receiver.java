package com.lyd.spring.boot.amqp.consumer;

import com.lyd.spring.boot.amqp.config.RabbitConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/9/30 ProjectName:spring-boot-cloud Version: 1.0
 */
@Slf4j
//@Component
@RabbitListener(bindings = {
        @QueueBinding(
                value = @Queue("annotationQueue"),
                exchange = @Exchange(value = "annotationExchange",type = ExchangeTypes.TOPIC,durable = "true")
        )
})
public class Receiver {

    @RabbitHandler
    public void process(String message){
        log.info("call process message:{}",message);
    }

}
