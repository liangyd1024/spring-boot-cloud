package com.lyd.spring.boot.redis.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * 描述：
 * <p>
 * #
 * </p>
 *
 * @author: lyd Date: 2017/12/18 ProjectName:spring-boot-cloud Version: 1.0
 */
@Configuration
@Slf4j
public class RestfulFactory {

    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.setErrorHandler(responseErrorHandler);
        return restTemplate;
    }

//    @Bean
//    public ResponseErrorHandler responseErrorHandler(){
//        return new ResponseErrorHandler() {
//            @Override
//            public boolean hasError(ClientHttpResponse response) throws IOException {
//                return false;
//            }
//
//            @Override
//            public void handleError(ClientHttpResponse response) throws IOException {
//            }
//        };
//    }
}
