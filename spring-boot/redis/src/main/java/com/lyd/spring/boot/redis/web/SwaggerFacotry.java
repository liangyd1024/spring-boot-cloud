package com.lyd.spring.boot.redis.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 描述：
 * <p>
 * #
 * </p>
 *
 * @author: lyd Date: 2018/1/24 ProjectName:spring-boot-cloud Version: 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerFacotry {


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()  // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.basePackage("com.lyd.spring.boot.redis.web.controller")) // 对所有api进行监控
                .paths(PathSelectors.any()) // 对所有路径进行监控
                .build()
                .apiInfo(apiInfo());
    }


    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().
                title("redis").
                description("xx").
                version("V1.0.0").
                termsOfServiceUrl("Redis Of Service").
                license("lyd").
                licenseUrl("").
                build();
    }

}
