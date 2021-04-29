package com.xy.maill.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.xy.maill.member.dao")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.xy.maill.member.feign")
public class MaillMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaillMemberApplication.class, args);
    }

}
