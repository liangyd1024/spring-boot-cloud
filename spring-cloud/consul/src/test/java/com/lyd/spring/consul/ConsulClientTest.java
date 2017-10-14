package com.lyd.spring.consul;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.agent.model.NewService;
import com.ecwid.consul.v1.agent.model.Service;
import com.ecwid.consul.v1.kv.model.GetValue;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/10/12 ProjectName:spring-boot-cloud Version: 1.0
 */
@Slf4j
public class ConsulClientTest {

    private ConsulClient consulClient;


    @Before
    public void init(){
        consulClient = new ConsulClient();
    }


    @Test
    public void agent_test(){
        Response<Map<String, Service>> response = consulClient.getAgentServices();
        log.info("call getAgentServices response:{}",response);
    }


    @Test
    public void kv_test(){
        Response<Boolean> response = consulClient.setKVValue("name","lyd");
        log.info("call setKVValue response:{}",response);

        Response<GetValue> response1 = consulClient.getKVValue("name");
        log.info("call getKVValue response:{}",response1);
    }


    @Test
    public void services_test(){
        Response<Void> response = consulClient.agentServiceDeregister("consulClientId");
        log.info("call agentServiceDeregister response:{}",response);

        NewService newService = new NewService();
        newService.setId("consulClientId");
        newService.setName("consulClientName");
        newService.setAddress("127.0.0.1");
        newService.setPort(8999);
        newService.setTags(Arrays.asList("a", "b"));

        NewService.Check check = new NewService.Check();
        check.setHttp("http://localhost:8999/consul/home");
        check.setInterval("5s");
        newService.setCheck(check);

        response = consulClient.agentServiceRegister(newService);
        log.info("call agentServiceRegister response:{}",response);
    }

}
