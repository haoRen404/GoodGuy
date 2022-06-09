package com.goodguy.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient // nacos 开启服务注册与发现功能
@SpringBootApplication
public class GoodguyControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodguyControllerApplication.class, args);
    }

}
