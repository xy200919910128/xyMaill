package com.xy.maill.coupon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.xy.maill.coupon.dao")
@EnableDiscoveryClient
public class MaillCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaillCouponApplication.class, args);
    }

}
