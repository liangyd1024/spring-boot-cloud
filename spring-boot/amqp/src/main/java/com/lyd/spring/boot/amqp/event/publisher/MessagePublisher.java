package com.lyd.spring.boot.amqp.event.publisher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;

/**
 * Created by aaa on 2017/10/4.
 */
@Slf4j
public class MessagePublisher implements ApplicationEventPublisher {


    @Override
    public void publishEvent(ApplicationEvent applicationEvent) {
        log.info("call publishEvent applicationEvent:{}",applicationEvent);
    }

    @Override
    public void publishEvent(Object o) {
        log.info("call publishEvent Object:{}",o);
    }
}
