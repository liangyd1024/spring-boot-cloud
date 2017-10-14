package com.lyd.spring.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/9/21 ProjectName:spring-boot-cloud Version: 1.0
 */
@SpringBootApplication
@EnableZuulProxy
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

}
