package com.xy.maill.thirdParty;
import com.aliyun.oss.OSSClient;
import com.xy.maill.maillThirdParty.ThirdPartyApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.InputStream;

@RunWith(value = SpringRunner.class)
@SpringBootTest(classes={ThirdPartyApplication.class})
class MaillProductApplicationTests {

    //对象存储的client 在上传得地方调用即可
    @Autowired
    private OSSClient ossClient;

    @Test
    void contextLoads() throws  Exception{
        InputStream inputStream = new FileInputStream("C:\\ariix_file\\1.jpg");
        ossClient.putObject("xymaill","usa.png",inputStream);
        ossClient.shutdown();
        System.out.println("success");
    }

}
