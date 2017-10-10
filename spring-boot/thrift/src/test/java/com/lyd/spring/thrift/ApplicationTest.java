package com.lyd.spring.thrift;

import com.lyd.spring.thrift.gen.facade.UserFacade;
import com.lyd.spring.thrift.gen.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/10/10 ProjectName:spring-boot-cloud Version: 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
@Slf4j
public class ApplicationTest {

    private User user;

    @Before
    public void init(){
        user = new User();
        user.setUsername("lyd");
        user.setPassword("123");
    }

    @Test
    public void thrift_test(){
        log.info("call test");
        try (TSocket transport = new TSocket("127.0.0.1", 7911)) {
            transport.open();
            UserFacade.Client client = new UserFacade.Client(new TBinaryProtocol(transport));
            log.info("call client.reg user:{}", user);
            String result = client.reg(user);
            log.info("call client.reg result:{}", result);

            log.info("call client.find user:{}", user);
            List<User> userList = client.find(user.getUsername());
            log.info("call client.find userList:{}", userList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
