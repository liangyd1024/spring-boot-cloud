package com.lyd.spring.boot.dubbo.provider.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * 描述：
 * <p>
 * #
 * </p>
 *
 * @author: lyd Date: 2018/1/25 ProjectName:spring-boot-cloud Version: 1.0
 */
//@Configuration
@PropertySource("classpath:dubbo/dubbo.properties")
@ImportResource(value = "classpath:dubbo/*.xml")
public class DubboConfig {

}
