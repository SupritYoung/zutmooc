package com.atguigu.eduorder.client;

import com.atguigu.commonutils.ordervo.CourseWebVoOrder;
//import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduCourse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(name = "service-edu")
public interface EduClient {

    //根据课程id查询课程信息
    @PostMapping("/eduservice/coursefront/getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable("id") String id);

    //根据课程id查询出对应课程
    @GetMapping("/eduservice/course/findCourseById/{id}")
    public EduCourse findCourseById(@PathVariable("id") String id);
}
