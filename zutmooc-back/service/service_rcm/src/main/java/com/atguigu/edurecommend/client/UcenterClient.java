package com.atguigu.edurecommend.client;

import com.atguigu.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(name = "service-ucenter")
public interface UcenterClient {

    @GetMapping("/educenter/member/getAllUsers")
    public R getAllUsers();
}
