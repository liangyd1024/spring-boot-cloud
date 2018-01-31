package com.lyd.spring.boot.dubbo.consumer.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lyd.spring.boot.dubbo.provider.facade.HelloWorldFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 描述：
 * <p>
 * #
 * </p>
 *
 * @author: lyd Date: 2018/1/29 ProjectName:spring-boot-cloud Version: 1.0
 */
@Controller
@RequestMapping("dubbo")
@Slf4j
public class DubboController {


    @Reference(timeout = 10000)
    private HelloWorldFacade helloWorldFacade;


    @RequestMapping("hello/{con}")
    public String helloWorld(@PathVariable String con){
        log.info("call helloWorld helloWorldFacade:{}",helloWorldFacade);
        return helloWorldFacade.print(con);
    }

}
