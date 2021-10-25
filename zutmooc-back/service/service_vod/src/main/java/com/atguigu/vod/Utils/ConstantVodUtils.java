package com.atguigu.vod.Utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//用来读取配置文件
@Component
public class ConstantVodUtils implements InitializingBean {
    @Value("${aliyun.vod.file.keyid}")
    private String keyid;

    @Value("${aliyun.vod.file.keysecret}")
    private String keysecret;

    public static String ACCESS_KEY_SECRET = "rABttdw6hx2ta3r0xUNHy6UH0OOdLY";
    public static String ACCESS_KEY_ID = "LTAI5t76MrQpNojudEebUq4C";

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = keyid;
        ACCESS_KEY_SECRET = keysecret;
    }
}
