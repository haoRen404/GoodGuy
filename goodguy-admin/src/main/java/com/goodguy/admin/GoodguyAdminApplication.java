package com.goodguy.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("com.goodguy.admin.dao") // mybatis-plus 扫描包
@EnableFeignClients(basePackages="com.goodguy.admin.feign")  // 开启 OpenFeign 远程调用
@EnableDiscoveryClient // nacos 开启服务注册与发现功能
@SpringBootApplication
public class GoodguyAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodguyAdminApplication.class, args);
    }

}
