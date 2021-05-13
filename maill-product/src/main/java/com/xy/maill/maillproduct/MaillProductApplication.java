package com.xy.maill.maillproduct;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.xy.maill.maillproduct.dao")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.xy.maill.maillproduct.feign")
public class MaillProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaillProductApplication.class, args);
    }

}
