package com.lyd.spring.consumer.service;

import com.lyd.spring.consumer.web.bean.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
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
class HelloServiceImpl implements HelloService{

    public String hello(@RequestParam(value = "name") String name) {
        return "这是熔断后返回的结果";
    }

    public User login(User user) {
        System.out.println("这是熔断后返回的结果");
        return user;
    }


}
