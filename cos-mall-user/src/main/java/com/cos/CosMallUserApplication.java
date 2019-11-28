package com.cos;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableDubbo
@EnableAsync
public class CosMallUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(CosMallUserApplication.class, args);
    }

}
