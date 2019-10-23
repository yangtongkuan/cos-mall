package com.cos;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class CosMallCommentApplication {

    public static void main(String[] args) {
        SpringApplication.run(CosMallCommentApplication.class, args);
    }

}
