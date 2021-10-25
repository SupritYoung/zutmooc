package com.atguigu.edurecommend.service.impl;

import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.edurecommend.client.EduClient;
import com.atguigu.edurecommend.client.OrderClient;
import com.atguigu.edurecommend.client.UcenterClient;
import com.atguigu.edurecommend.entity.RcmLike;
import com.atguigu.edurecommend.mapper.RcmLikeMapper;
import com.atguigu.edurecommend.service.RcmLikeService;
import com.atguigu.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author suprit
 * @since 2021-04-23
 */
@Service
public class RcmLikeServiceImpl extends ServiceImpl<RcmLikeMapper, RcmLike> implements RcmLikeService {
    @Autowired
    UcenterClient ucenterClient;

    @Autowired
    EduClient eduClient;

    @Autowired
    OrderClient orderClient;

    @Override
    public ArrayList<UcenterMember> getAllUsers() {
        ucenterClient.getAllUsers();
        Map<String, Object> data = ucenterClient.getAllUsers().getData();
        ArrayList<UcenterMember> list = (ArrayList<UcenterMember>) data.get("list");
        return list;
    }

    @Override
    public ArrayList<EduCourse> getAllItems() {
        Map<String, Object> data = eduClient.getAllCourse().getData();
        ArrayList<EduCourse> list = (ArrayList<EduCourse>) data.get("list");
        return list;
    }

    @Override
    public ArrayList<EduCourse> findLikesItemsByUser(String id) {
        Map<String, Object> data = orderClient.findLikesItemsByUser(id).getData();
        ArrayList<EduCourse> list = (ArrayList<EduCourse>) data.get("list");
        return list;
    }
}
