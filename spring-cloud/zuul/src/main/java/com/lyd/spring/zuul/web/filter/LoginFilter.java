package com.lyd.spring.zuul.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/9/29 ProjectName:spring-boot-cloud Version: 1.0
 */
@Slf4j
@Component
public class LoginFilter extends ZuulFilter {

    @Override
    public String filterType() {
        log.info("call filterType");
        return "pre";
    }

    @Override
    public int filterOrder() {
        log.info("call filterOrder");
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        log.info("call shouldFilter");
        return true;
    }

    @Override
    public Object run() {
        log.info("call run");
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        log.info("call request method:{},url:{}",request.getMethod(),request.getRequestURI());
        return null;
    }
}
