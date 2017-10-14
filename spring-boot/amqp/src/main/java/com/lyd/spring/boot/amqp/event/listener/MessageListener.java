package com.lyd.spring.boot.amqp.event.listener;

import com.lyd.spring.boot.amqp.event.event.MessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;

/**
 * Created by aaa on 2017/10/4.
 */
@Slf4j
public class MessageListener implements ApplicationListener<MessageEvent> {

    @Override
    public void onApplicationEvent(MessageEvent messageEvent) {
        log.info("call onApplicationEvent messageEvent:{}",messageEvent);
    }
}
