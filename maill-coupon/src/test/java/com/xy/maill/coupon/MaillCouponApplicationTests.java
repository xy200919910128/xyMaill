package com.xy.maill.coupon;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xy.maill.coupon.entity.CouponEntity;
import com.xy.maill.coupon.service.CouponService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class MaillCouponApplicationTests {

    @Autowired
    private CouponService couponService;


    @Test
    void contextLoads() {
        CouponEntity couponEntity1 =  couponService.getOne(new QueryWrapper<CouponEntity>().eq("coupon_name","qwer"));
        System.out.println(couponEntity1);
    }

}
