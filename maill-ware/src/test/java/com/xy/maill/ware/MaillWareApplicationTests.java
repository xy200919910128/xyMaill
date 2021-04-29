package com.xy.maill.ware;

import com.xy.maill.ware.entity.WareInfoEntity;
import com.xy.maill.ware.service.WareInfoService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class MaillWareApplicationTests {

    @Autowired
    private WareInfoService wareInfoService;

    @Test
    void contextLoads() {
        WareInfoEntity wareInfoEntity = new WareInfoEntity();
        wareInfoEntity.setName("fdsf");
        wareInfoService.save(wareInfoEntity);
        System.out.println("成功");
    }

}
