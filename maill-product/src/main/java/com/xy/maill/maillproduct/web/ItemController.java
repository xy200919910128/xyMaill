package com.xy.maill.maillproduct.web;

import com.alibaba.nacos.common.util.UuidUtils;
import com.xy.maill.maillproduct.entity.CategoryEntity;
import com.xy.maill.maillproduct.service.CategoryService;
import com.xy.maill.maillproduct.vo.Catelog2Vo;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/product")
public class ItemController {

    @Autowired
    private  CategoryService categoryService;
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @GetMapping({"/{skuId}.html"})
    public String indexPage(Long skuId,Model model) {
        System.out.println("查询商品详情");
        return "item";
    }

}
