package com.xy.maill.ware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.xy.maill.ware.dao")
@EnableDiscoveryClient
@EnableFeignClients
public class MaillWareApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaillWareApplication.class, args);
    }

}
