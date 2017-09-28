package com.lyd.spring.consumer.service;

import com.lyd.spring.provider.facade.api.CloudFacade;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/9/28 ProjectName:spring-boot-cloud Version: 1.0
 */
@FeignClient("myCloudProvider")
public interface CloudService extends CloudFacade {
}
