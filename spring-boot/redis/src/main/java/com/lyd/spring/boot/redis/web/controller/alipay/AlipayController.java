package com.lyd.spring.boot.redis.web.controller.alipay;

import com.alipay.api.AlipayClient;
import com.lyd.spring.boot.redis.web.config.AlipayConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 描述：
 * <p>
 * #
 * </p>
 *
 * @author: lyd Date: 2018/1/15 ProjectName:spring-boot-cloud Version: 1.0
 */
@Slf4j
public class AlipayController {


    @Autowired
    protected AlipayClient alipayClient;

    @Autowired
    protected AlipayConfig alipayConfig;

    protected String classPath = this.getClass().getClassLoader().getResource("").getPath();

    protected String buildCallBackUrl(String url){
        return alipayConfig.getSelfDomainPrefix()+url;
    }

    protected String buildPage(String pageName){
        return "templates/alipay/"+pageName;
    }

}
