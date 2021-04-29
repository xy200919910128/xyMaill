//package com.xy.maill.maillproduct;
//
//import com.aliyun.oss.OSSClient;
//import com.xy.maill.maillproduct.entity.BrandEntity;
//import com.xy.maill.maillproduct.service.BrandService;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.FileInputStream;
//import java.io.InputStream;
//
//@RunWith(value = SpringRunner.class)
//@SpringBootTest(classes={MaillProductApplication.class})
//class MaillProductApplicationTests {
//
//    @Autowired
//    private BrandService brandService;
//
//    //对象存储的client 在上传得地方调用即可
//    @Autowired
//    private OSSClient ossClient;
//
//    @Test
//    void contextLoads() throws  Exception{
////        brandService.getById(1);
////        System.out.println(1);
//        InputStream inputStream = new FileInputStream("C:\\ariix_file\\usa.png");
//        ossClient.putObject("xymaill","usa.png",inputStream);
//        ossClient.shutdown();;
//        System.out.println("success");
//    }
//
//}
