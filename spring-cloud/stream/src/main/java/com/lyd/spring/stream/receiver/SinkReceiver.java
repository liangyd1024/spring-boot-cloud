package com.lyd.spring.stream.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/10/9 ProjectName:spring-boot-cloud Version: 1.0
 */
@EnableBinding(Sink.class)
@Slf4j
public class SinkReceiver {


    @StreamListener(Sink.INPUT)
    public void receiver(Object payload){
        log.info("call receiver payload:{}",payload);
    }

}
