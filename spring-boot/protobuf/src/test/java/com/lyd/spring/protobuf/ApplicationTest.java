package com.lyd.spring.protobuf;

import com.lyd.spring.protobuf.gen.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/10/11 ProjectName:spring-boot-cloud Version: 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
@Slf4j
public class ApplicationTest {


    @Test
    public void protobuf_serialize() throws Exception {
        User.Builder builder = User.newBuilder();
        builder.setUsername("lydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlydlyd");
        builder.setPassword("123123123123123123123123123123123123123123123123" +
                "123123123123123123123123123123123123123123123123" +
                "123123123123123123123123123123123123123123123123");
        User user = builder.build();
        int size = 0;
        long start = System.currentTimeMillis();
        for(int i =0;i<15000000;i++){
            byte[] result = user.toByteArray();
            size = result.length;
            User tempUser = User.parseFrom(result);
        }
        long end = System.currentTimeMillis();
        log.info("call start:{},end:{},result:{},size:{}",start,end,end-start,size);
    }

}
