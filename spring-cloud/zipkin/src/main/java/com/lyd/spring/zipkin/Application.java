package com.lyd.spring.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableZipkinServer
public class Application {

    public static void main( String[] args ){
        SpringApplication.run(Application.class,args);
    }
}

