package com.lyd.spring.config.client.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/9/20 ProjectName:spring-boot-cloud Version: 1.0
 */
@RestController
@RequestMapping("/config")
@RefreshScope//刷新配置
@Slf4j
public class ConfigController {

    @Value("${name}")
    private String name;


    @RequestMapping("/get")
    @ResponseBody
    public String getConfig(){
        log.info("call getConfig name:{}",name);
        return name;
    }

}
