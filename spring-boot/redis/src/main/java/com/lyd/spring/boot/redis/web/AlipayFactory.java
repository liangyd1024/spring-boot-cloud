package com.lyd.spring.boot.redis.web;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.lyd.spring.boot.redis.web.config.AlipayConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述：
 * <p>
 * #
 * </p>
 *
 * @author: lyd Date: 2018/1/17 ProjectName:spring-boot-cloud Version: 1.0
 */
@Configuration
@Slf4j
public class AlipayFactory {



    @Bean
    public AlipayClient alipayClient(AlipayConfig alipayConfig){
        return new DefaultAlipayClient(
                alipayConfig.getGatewayUrl(),
                alipayConfig.getAppId(),
                alipayConfig.getPrivateKey(),
                "json",
                "UTF-8",
                alipayConfig.getAlipayPublicKey(),
                "RSA2");
    }


    @Bean
    public AlipayConfig alipayConfig(){
        return new AlipayConfig();
    }


}
