package com.lyd.spring.boot.amqp.event.event;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/10/9 ProjectName:spring-boot-cloud Version: 1.0
 */
@Data
public class MessageEvent extends ApplicationEvent {

    private String name;

    public MessageEvent(Object source) {
        super(source);
    }

}
