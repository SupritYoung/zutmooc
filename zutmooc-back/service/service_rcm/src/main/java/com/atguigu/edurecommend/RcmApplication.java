package com.atguigu.edurecommend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@EnableDiscoveryClient //开启nacos注册
@EnableFeignClients    //开启服务调用
@MapperScan("com.atguigu.edurecommend.mapper")
@ComponentScan(basePackages = {"com.atguigu"}) //修改包扫描规则。如果不加，其他模块的配置类加载不到
public class RcmApplication {
    public static void main(String[] args) {
        SpringApplication.run(RcmApplication.class, args);
    }
}