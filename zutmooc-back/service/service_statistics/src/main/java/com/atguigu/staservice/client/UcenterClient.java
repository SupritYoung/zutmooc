package com.atguigu.staservice.client;

import com.atguigu.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    //查询某一天的注册方法
    @GetMapping("/educenter/member/getCountResigiter/{day}")
    public R getCountResigiter(@PathVariable("day") String day);
}