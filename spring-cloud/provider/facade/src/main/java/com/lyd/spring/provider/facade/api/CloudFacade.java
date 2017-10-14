package com.lyd.spring.provider.facade.api;

import com.lyd.spring.provider.facade.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/9/28 ProjectName:spring-boot-cloud Version: 1.0
 */
@RequestMapping("/cloud")
public interface CloudFacade {

    /**
     * register
     * @param user  user
     * @return  User
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public User register(@RequestBody User user);

    /**
     * getAll
     * @param userName  userName
     * @return  List<User>
     */
    @RequestMapping(value = "/getAll")
    public List<User> getAll(@RequestParam(value = "userName") String userName);

}
