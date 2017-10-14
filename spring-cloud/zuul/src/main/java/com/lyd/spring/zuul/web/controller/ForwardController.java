package com.lyd.spring.zuul.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/9/29 ProjectName:spring-boot-cloud Version: 1.0
 */
@RestController
@Slf4j
public class ForwardController {


    @RequestMapping("/forward/hello")
    public String forward(@RequestParam String name){
        log.info("call forward name:{}",name);
        return name;
    }


}
