package com.kjde1033.bqgnmgt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).
                select().apis(RequestHandlerSelectors.basePackage("com.kjde1033.bqgnmgt.controller")).
                paths(PathSelectors.any()).build();
    }

    @Bean
    public ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("KJDE1033-调试界面").
                contact(new Contact("王好明","www.baidu.com","xxxx.com")).
                version("1.1").description("王好明测试页").build();
    }
}
