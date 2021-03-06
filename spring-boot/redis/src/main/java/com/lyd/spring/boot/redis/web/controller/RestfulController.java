package com.lyd.spring.boot.redis.web.controller;

import com.lyd.spring.boot.redis.web.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/9/20 ProjectName:spring-boot-cloud Version: 1.0
 */
@RestController
@Slf4j
public class RestfulController {


    @Value("${key.secret}")
    private String secret;


    @RequestMapping("/hello")
    public String hello(HttpSession session){
       log.info("call hello secret:{},sessionId:{}",secret,session.getId());
       return secret+"-"+session.getId();
    }


    @RequestMapping("/user/{userName}")
    @Cacheable(value="user-key")
    public String cache(@PathVariable String userName){
        log.info("call cache userName:{}",userName);
        return "cache";
    }

    @Bean
    public User user(@Qualifier("object") Object object){
        log.info("call object:{},obj:{}",object,this.object());
        return new User();
    }

    @Bean
    public Object object(){
        return new Object();
    }

}
