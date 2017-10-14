package com.lyd.spring.thrift.service;

import com.lyd.spring.thrift.gen.facade.UserFacade;
import com.lyd.spring.thrift.gen.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/10/10 ProjectName:spring-boot-cloud Version: 1.0
 */
@Service
@Slf4j
public class UserService implements UserFacade.Iface {

    private final static Map<String,User> USER_MAP = new HashMap<>();

    @Override
    public String reg(User user) throws TException {
        log.info("call reg user:{}",user);
        USER_MAP.put(user.getUsername(),user);
        return user.getPassword();
    }

    @Override
    public List<User> find(String username) throws TException {
        log.info("call find username:{}",username);
        if(!USER_MAP.containsKey(username)){
            return null;
        }
        return Collections.singletonList(USER_MAP.get(username));
    }
}
