package com.lyd.spring.boot.amqp.test;

import com.lyd.spring.boot.amqp.producer.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/9/30 ProjectName:spring-boot-cloud Version: 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MessageTest {

    @Autowired
    private Sender sender;

    @Test
    public void testSender() {
        Arrays.asList(1,2,3,4,5,6,7,8,9).forEach(i -> {
            try {
                sender.send(LocalDateTime.now().toString());
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

}
