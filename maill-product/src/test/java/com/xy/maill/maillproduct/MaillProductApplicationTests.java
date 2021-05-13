package com.xy.maill.maillproduct;

import com.xy.maill.maillproduct.entity.BrandEntity;
import com.xy.maill.maillproduct.service.BrandService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

@SpringBootTest(classes={MaillProductApplication.class})
public class MaillProductApplicationTests {

    @Autowired
    private BrandService brandService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private  RedissonClient redissonClient;

    @Test
    public void cacche(){
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        stringStringValueOperations.set("hello","world"+ UUID.randomUUID().toString());

        System.out.println(stringStringValueOperations.get("hello"));
    }

    public void testRedisson(){
        System.out.println(redissonClient);
    }

}
