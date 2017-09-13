package com.lyd.spring.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/9/13 ProjectName:spring-boot-cloud Version: 1.0
 */
@RestController
public class RestfulController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

}
