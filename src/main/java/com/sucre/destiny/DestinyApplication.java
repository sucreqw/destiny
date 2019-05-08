package com.sucre.destiny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sucre.destiny.controller","com.sucre.destiny.service"})
public class DestinyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DestinyApplication.class, args);
    }

}
