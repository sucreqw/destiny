package com.sucre.destiny;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sucre.destiny.controller", "com.sucre.destiny.service", "com.sucre.destiny.config", "com.sucre.destiny.dao"})

public class DestinyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DestinyApplication.class, args);
    }

}
