package com.lyd.spring.boot.redis.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 描述：
 * <p>
 * #
 * </p>
 *
 * @author: lyd Date: 2018/1/17 ProjectName:spring-boot-cloud Version: 1.0
 */
@ConfigurationProperties(prefix = "alipay")
@Data
public class AlipayConfig {


    private String gatewayUrl;

    private String appId;

    private String privateKey;

    private String publicKey;

    private String alipayPublicKey;

    private String selfDomainPrefix;

    private String signType;

}
