package com.lyd.spring.consumer.service;

import com.lyd.spring.provider.facade.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/9/18 ProjectName:spring-boot-cloud Version: 1.0
 */
@FeignClient(name="myCloudProvider",fallback = HelloServiceImpl.class)
public interface HelloService {

    @RequestMapping(value = "/hello")
    public String hello(@RequestParam(value = "name") String name);

    @RequestMapping(value = "/login")
    public User login(User user);

}


@Component
@Slf4j
class HelloServiceImpl implements HelloService{

    @Override
    public String hello(@RequestParam(value = "name") String name) {
        log.info("call hello 这是熔断后返回的结果");
        return name;
    }

    @Override
    public User login(User user) {
        log.info("call login 这是熔断后返回的结果");
        return user;
    }


}
