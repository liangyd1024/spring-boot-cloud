package com.lyd.spring.consul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/10/11 ProjectName:spring-boot-cloud Version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

}
