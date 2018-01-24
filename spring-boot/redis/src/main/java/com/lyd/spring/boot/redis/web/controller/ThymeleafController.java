package com.lyd.spring.boot.redis.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 描述：
 * <p>
 * #
 * </p>
 *
 * @author: lyd Date: 2018/1/22 ProjectName:spring-boot-cloud Version: 1.0
 */
@Controller
@RequestMapping("th")
@Slf4j
public class ThymeleafController {


    @RequestMapping("")
    public String index(ModelMap modelMap,
                        Model model,
                        ModelAndView modelAndView,
                        HttpServletRequest request,
                        HttpSession session){
        modelMap.addAttribute("requestParam","modelMap");
        model.addAttribute("requestParam","model");
        request.setAttribute("requestParam","request");
        session.setAttribute("sessionParam","session");
        List classList = SpringFactoriesLoader.loadFactories(SpringApplicationRunListener.class.getClass(),SpringApplicationRunListener.class.getClass().getClassLoader());
        log.info("classList:{}",classList);
        return "thymeleaf";
    }


}
