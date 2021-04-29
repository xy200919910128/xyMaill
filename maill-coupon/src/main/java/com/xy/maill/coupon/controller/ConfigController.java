package com.xy.maill.coupon.controller;


import com.xy.maill.common.utils.R;
import com.xy.maill.coupon.entity.CouponEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("configController")
public class ConfigController {

    @Value("${spring.application.name}")
    private String dbUrl;

    /**
     * 信息
     */
    @RequestMapping("/info")
    public R info(){
        String url = dbUrl;

        return R.ok().put("url", url);
    }


}
