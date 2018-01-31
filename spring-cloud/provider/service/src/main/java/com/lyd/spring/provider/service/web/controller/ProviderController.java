package com.lyd.spring.provider.service.web.controller;

import com.lyd.spring.provider.facade.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/9/18 ProjectName:spring-boot-cloud Version: 1.0
 */
@RestController
@Slf4j
public class ProviderController {

    @RequestMapping("/hello")
    public String say(HttpServletRequest request,@RequestParam String name) throws InterruptedException {
        log.info("call say name:{},{},{},{},{},{}",name,
                request.getHeader("X-B3-TraceId"),request.getHeader("X-B3-SpanId"),
                request.getHeader("X-B3-ParentSpanId"),request.getHeader("X-B3-Sampled"),
                request.getHeader("X-Span-Name"));
        return "my name is "+name;
    }

    @RequestMapping("/login")
    public User login(@RequestBody User user){
        log.info("call login user:{}",user);
        user.setUserName("call login user");
        return user;
    }

    @RequestMapping("/timeout")
    public String timeout() throws InterruptedException {
        //模拟服务超时
        int timeout = new Random().nextInt(5)+1;
        log.info("call timeout start:{}",timeout);
        TimeUnit.SECONDS.sleep(timeout);
        log.info("call timeout end:{}",timeout);
        return "my timeout is sleep:"+timeout;
    }


}
