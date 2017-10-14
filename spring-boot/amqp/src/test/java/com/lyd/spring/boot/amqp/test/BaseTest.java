package com.lyd.spring.boot.amqp.test;

import com.lyd.spring.boot.amqp.Application;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by aaa on 2017/10/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public abstract class BaseTest {

    private final Object lock = new Object();

    protected void lock()  {
        synchronized (lock){
            while (true){
                try {
                    lock.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
