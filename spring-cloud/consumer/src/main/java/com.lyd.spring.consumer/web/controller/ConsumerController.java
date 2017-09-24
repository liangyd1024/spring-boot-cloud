package com.lyd.spring.consumer.web.controller;

import com.lyd.spring.consumer.service.HelloService;
import com.lyd.spring.consumer.web.bean.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/9/18 ProjectName:spring-boot-cloud Version: 1.0
 */
@RestController
@Slf4j
public class ConsumerController {

    @Autowired
    private HelloService helloService;

    @Autowired
    private RestTemplate restTemplate;


    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


    @RequestMapping("/consumer/{name}")
    public String consumer(@PathVariable String name){
        log.info("call consumer name:{}",name);
        return helloService.hello(name);
    }

    @RequestMapping("/consumer/login")
    public User login(User user){
        log.info("call login user:{}",user);
        return helloService.login(user);
    }

    @RequestMapping("/ribbonHello/{name}")
    public String ribbonHello(@PathVariable String name){
        log.info("call ribbonHello name:{}",name);
        return restTemplate.getForEntity("http://myCloudProvider/hello?name={name}",String.class,name).getBody();
    }

    @RequestMapping("/ribbonLogin/login")
    public User ribbonLogin(@RequestParam String userName,@RequestParam String password){
        log.info("call ribbonLogin userName:{},password:{}",userName,password);
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        return restTemplate.postForObject("http://myCloudProvider/login",user,User.class);
    }


    @RequestMapping("/ribbonTimeout/timeout")
    @HystrixCommand(fallbackMethod = "ribbonTimeoutCallBack")
    public String ribbonTimeout(){
        log.info("call ribbonTimeout");
        String result = restTemplate.getForObject("http://myCloudProvider/timeout",String.class,"");
        log.info("call ribbonTimeout result:{}",result);
        return result;
    }

    private String ribbonTimeoutCallBack(Throwable throwable){
        log.info("call ribbonTimeoutCallBack throwable:{}",throwable);
        return "这是超时后的熔断处理";
    }
}
