package com.lyd.spring.boot.controller;

import com.lyd.spring.BaseTest;
import com.lyd.spring.boot.web.controller.RestfulController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/9/15 ProjectName:spring-boot-cloud Version: 1.0
 */
@Slf4j
public class RestfulControllerTest extends BaseTest {

    private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Mock
    private RedisTemplate<String,String> redisTemplate;

    @Before
    public void init(){
        log.info("call RestfulControllerTest init");
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new RestfulController(),webApplicationContext).build();

    }

    @Test
    public void test_hello() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.
                        get("/hello").
                        accept(MediaType.APPLICATION_JSON)).
                andDo(MockMvcResultHandlers.print()).
                andReturn();
    }

    @Test
    public void test_user() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.
                        get("/user/123").
                        accept(MediaType.APPLICATION_JSON)).
                andDo(MockMvcResultHandlers.print()).
                andReturn();
    }
}
