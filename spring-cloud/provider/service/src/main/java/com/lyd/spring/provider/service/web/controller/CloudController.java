package com.lyd.spring.provider.service.web.controller;

import com.google.common.collect.Lists;
import com.lyd.spring.provider.facade.api.CloudFacade;
import com.lyd.spring.provider.facade.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/9/28 ProjectName:spring-boot-cloud Version: 1.0
 */
@RestController
@Slf4j
public class CloudController implements CloudFacade{


    @Override
    public User register(@RequestBody User user) {
        log.info("call register user:{}",user);
        return user;
    }

    @Override
    public List<User> getAll(@RequestParam(value = "userName") String userName) {
        log.info("call register userName:{}",userName);
        return Lists.newArrayList(new User(userName,"123"),new User(userName,"456"));
    }
}
