package com.lyd.spring.admin;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/9/21 ProjectName:spring-boot-cloud Version: 1.0
 */
@SpringBootApplication
@EnableScheduling
@EnableAdminServer
@EnableDiscoveryClient
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

}
