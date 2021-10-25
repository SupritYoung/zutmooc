package com.atguigu.eduorder.service;

import com.atguigu.eduorder.entity.Order;
import com.atguigu.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-03-13
 */
public interface OrderService extends IService<Order> {

    //1 生成订单的方法
    String createOrders(String courseId, String memberIdByJwtToken);


    ArrayList<EduCourse> findLikesItemsByUser(String id);
}
