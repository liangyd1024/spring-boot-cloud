package com.lyd.spring.consumer.web.controller;

import com.lyd.spring.consumer.model.User;
import com.lyd.spring.consumer.service.CloudService;
import com.lyd.spring.consumer.service.HelloService;
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

import java.util.List;

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

    /**
     *  消费者手动包装模式-声明式
     */
    @Autowired
    private HelloService helloService;
    /**
     *  消费者手动包装模式-编程式
     */
    @Autowired
    private RestTemplate restTemplate;
    /**
     *  服务端包装模式-声明式
     */
    @Autowired
    private CloudService cloudService;


    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


    @RequestMapping("/declare/{name}")
    public String declareName(@PathVariable String name){
        log.info("call consumer name:{}",name);
        return helloService.hello(name);
    }


    @RequestMapping("/declare/login")
    public User declareLogin(@RequestParam String userName, @RequestParam String password){
        log.info("call login userName:{},password:{}",userName,password);
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        return helloService.login(user);
    }


    @RequestMapping("/program/{name}")
    public String programName(@PathVariable String name){
        log.info("call ribbonHello name:{}",name);
        return restTemplate.getForEntity("http://myCloudProvider/hello?name={name}",String.class,name).getBody();
    }


    @RequestMapping("/program/login")
    public User programLogin(@RequestParam String userName,@RequestParam String password){
        log.info("call ribbonLogin userName:{},password:{}",userName,password);
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        return restTemplate.postForObject("http://myCloudProvider/login",user,User.class);
    }


    @RequestMapping("/program/timeout")
    @HystrixCommand(fallbackMethod = "ribbonTimeoutCallBack")
    public String ribbonTimeout(){
        log.info("call ribbonTimeout");
        String result = restTemplate.getForObject("http://myCloudProvider/timeout",String.class,"");
        log.info("call ribbonTimeout result:{}",result);
        return result;
    }

    //回调函数必须在同一个类中
    private String ribbonTimeoutCallBack(Throwable throwable){
        log.info("call ribbonTimeoutCallBack throwable:{}",throwable);
        return "这是超时后的熔断处理";
    }


    @RequestMapping("/server/declare/get-all")
    public List<User> serverDeclareGetAll(@RequestParam String userName){
        log.info("call serverDeclareGetAll userName:{}",userName);
        return cloudService.getAll(userName);
    }

    @RequestMapping("/server/declare/register")
    public User serverDeclareRegister(@RequestParam String userName,@RequestParam String password){
        log.info("call serverDeclareRegister userName:{},password:{}",userName,password);
        return cloudService.register(new User(userName,password));
    }

}
