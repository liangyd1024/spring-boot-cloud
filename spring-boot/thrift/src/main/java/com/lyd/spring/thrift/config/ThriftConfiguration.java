package com.lyd.spring.thrift.config;

import com.lyd.spring.thrift.gen.facade.UserFacade;
import com.lyd.spring.thrift.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: lyd Date: 2017/10/10 ProjectName:spring-boot-cloud Version: 1.0
 */
@Configuration
@Slf4j
public class ThriftConfiguration {

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Autowired
    private TServer tServer;

    @PostConstruct
    public void init(){
        executorService.execute(() ->{
            log.info("call init execute tServer.serve");
            tServer.serve();
        });
    }


    @Bean
    public TServerTransport tServerTransport(){
        try {
            return new TServerSocket(7911);
        } catch (TTransportException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Bean
    public TServer tServer(TServerTransport tServerTransport, UserService userService){
        UserFacade.Processor processor = new UserFacade.Processor<>(userService);
        TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(
                tServerTransport).processor(processor));
        return server;
    }

}
