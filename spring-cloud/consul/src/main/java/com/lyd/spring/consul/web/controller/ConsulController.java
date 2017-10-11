package com.lyd.spring.consul.web.controller;

import lombok.extern.slf4j.Slf4j;
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


    @RequestMapping(value = "/home")
    public String home(){
        log.info("call home this is consul!!!");
        return "this is consul!!!";
    }

}
