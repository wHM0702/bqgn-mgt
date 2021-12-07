package com.kjde1033.bqgnmgt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.kjde1033.bqgnmgt.dao")
@EnableSwagger2
public class BqgnMgtApplication {

    public static void main(String[] args) {
        SpringApplication.run(BqgnMgtApplication.class, args);
    }

}
