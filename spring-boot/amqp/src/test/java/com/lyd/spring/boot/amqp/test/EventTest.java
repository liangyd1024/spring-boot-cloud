package com.lyd.spring.boot.amqp.test;

import com.lyd.spring.boot.amqp.Application;
import com.lyd.spring.boot.amqp.event.publisher.MessagePublisher;
import com.lyd.spring.boot.amqp.event.target.MessageEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by aaa on 2017/10/4.
 */
public class EventTest extends BaseTest{

    @Autowired
    private MessagePublisher messagePublisher;

    @Autowired
    private ApplicationContext applicationContext;

    private MessageEvent messageEvent;


    @Before
    public void init(){
        messageEvent = new MessageEvent(new EventTest());
        messageEvent.setName(this.toString());
    }

    @Test
    public void publisherEvent(){
        applicationContext.publishEvent(messageEvent);
//        messagePublisher.publishEvent(messageEvent);
    }

    @Test
    public void publisherObject(){
        applicationContext.publishEvent(this);
//        messagePublisher.publishEvent(this);
    }

    @After
    public void after(){
        this.lock();
    }
}
