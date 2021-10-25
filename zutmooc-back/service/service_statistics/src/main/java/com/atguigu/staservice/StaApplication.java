package com.atguigu.staservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.atguigu")
@MapperScan("com.atguigu.staservice.mapper")
@EnableFeignClients
@EnableDiscoveryClient
public class StaApplication {
    public static void main(String[] args) {
        SpringApplication.run(StaApplication.class);
    }
}
