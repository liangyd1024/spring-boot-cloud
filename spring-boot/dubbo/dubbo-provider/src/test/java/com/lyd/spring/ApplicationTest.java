package com.lyd.spring;

import com.alibaba.dubbo.config.annotation.Service;
import com.lyd.spring.boot.dubbo.provider.Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * Unit test for simple Application.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
@Slf4j
public class ApplicationTest {

    @Autowired
    private ApplicationContext applicationContext;


    @Test
    public void test(){
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(Service.class);
        log.info("call test beans:{}",beans);

    }


}
