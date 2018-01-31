package com.lyd.spring.boot.dubbo.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.lyd.spring.boot.dubbo.provider.facade.HelloWorldFacade;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述：
 * <p>
 * #
 * </p>
 *
 * @author: lyd Date: 2018/1/24 ProjectName:spring-boot-cloud Version: 1.0
 */
@Slf4j
@Service(interfaceClass = HelloWorldFacade.class)
public class HelloWorldService implements HelloWorldFacade{

    public HelloWorldService(){
        log.info("call HelloWorldService ......");
    }


    @Override
    public String print(String info) {
        log.info("call print info:{}",info);
        return "hello:"+info;
    }


}
