package com.atguigu.eduorder.service.impl;

import com.atguigu.commonutils.ordervo.CourseWebVoOrder;
import com.atguigu.commonutils.ordervo.UcenterMemberOrder;
import com.atguigu.eduorder.client.EduClient;
import com.atguigu.eduorder.client.UcenterClient;
import com.atguigu.eduorder.entity.Order;
import com.atguigu.eduorder.mapper.OrderMapper;
import com.atguigu.eduorder.service.OrderService;
import com.atguigu.eduorder.utils.OrderNoUtil;
import com.atguigu.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-03-13
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private EduClient eduClient;

    @Autowired
    private UcenterClient ucenterClient;

    //1 生成订单的方法
    @Override
    public String createOrders(String courseId, String memberId) {
        //通过远程调用 根据用户id获取用户信息
        UcenterMemberOrder userInfoOrder = ucenterClient.getUserInfoOrder(memberId);

        //通过远程调用 根据课程id获取课信息
        CourseWebVoOrder courseInfoOrder = eduClient.getCourseInfoOrder(courseId);

        //创建Order对象，向order对象里面设置需要数据
        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());//订单号
        order.setCourseId(courseId); //课程id
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName(courseInfoOrder.getTeacherName());
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(memberId);
        order.setMobile(userInfoOrder.getMobile());
        order.setNickname(userInfoOrder.getNickname());
        //高精度浮点数特殊的判等于0方式
        boolean isFree = (courseInfoOrder.getPrice().compareTo(BigDecimal.ZERO)) == 0;
        if(isFree)
            order.setStatus(1);  //课程免费直接设置为已支付
        else
            order.setStatus(0);  //订单状态（0：未支付 1：已支付）
        order.setPayType(1);  //支付类型 ，微信1
        baseMapper.insert(order);
         //返回订单号
        return order.getOrderNo();
    }

    //查询指定id用户购买过的全部课程
    @Override
    public ArrayList<EduCourse> findLikesItemsByUser(String id) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("member_id", id);
        //查询出当前用户的所有订单
        List<Order> orderList = baseMapper.selectList(wrapper);

        //根据每个订单的课程id查询出对应课程，组成该用户的课程列表
        ArrayList<EduCourse> result = new ArrayList<>();
        for (Order order : orderList) {
            EduCourse eduCourse = eduClient.findCourseById(order.getCourseId());
            result.add(eduCourse);
        }
        return result;
    }
}
