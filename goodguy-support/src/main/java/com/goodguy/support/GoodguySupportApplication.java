package com.goodguy.support;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages="com.goodguy.support.feign") // @EnableFeignClients 开启 OpenFeign 远程调用
@EnableDiscoveryClient // nacos 开启服务注册与发现功能
@SpringBootApplication
public class GoodguySupportApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodguySupportApplication.class, args);
    }

}
