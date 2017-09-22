package com.lyd.spring.consumer.web.controller;

import com.lyd.spring.consumer.service.HelloService;
import com.lyd.spring.consumer.web.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/9/18 ProjectName:spring-boot-cloud Version: 1.0
 */
@RestController
@Slf4j
public class ConsumerController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("/consumer/{name}")
    public String consumer(@PathVariable String name){
        log.info("call consumer name:{}",name);
        return helloService.hello(name);
    }

    @RequestMapping("/consumer/login")
    public User login(User user){
        log.info("call login user:{}",user);
        return helloService.login(user);
    }
}
