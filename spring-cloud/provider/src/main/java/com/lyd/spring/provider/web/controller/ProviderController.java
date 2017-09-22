package com.lyd.spring.provider.web.controller;

import com.lyd.spring.provider.web.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    public String say(@RequestParam String name){
        log.info("call say name:{}",name);
        return "my name is "+name;
    }

    @RequestMapping("/login")
    public User login(@RequestBody User user){
        log.info("call login user:{}",user);
        user.setUserName("call login user");
        return user;
    }


}
