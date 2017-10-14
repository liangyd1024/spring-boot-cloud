package com.lyd.spring.consul.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/10/11 ProjectName:spring-boot-cloud Version: 1.0
 */
@RestController
@RequestMapping("/consul")
@Slf4j
public class ConsulController {


    @Value("${a.b:defaultValue}")
    private String value;


    @RequestMapping(value = "/home")
    public String home(){
        log.info("call home this is consul!!!,value:{}",value);
        return "this is consul!!!";
    }

}
