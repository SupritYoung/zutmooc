package com.atguigu.edurecommend.client;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduCourse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Component
@FeignClient(name = "service-order")
public interface OrderClient {

    @GetMapping("/eduorder/order/findLikesCourseByUser/{id}")
    public R findLikesItemsByUser(@PathVariable("id") String id);
}
