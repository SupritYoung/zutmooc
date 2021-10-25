package com.atguigu.edurecommend.client;

import com.atguigu.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(name = "service-edu")
public interface EduClient {
    @GetMapping("/eduservice/course/getCourseList")
    public R getAllCourse();
}
