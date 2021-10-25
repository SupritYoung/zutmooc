package com.atguigu.edurecommend.service;

import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.edurecommend.entity.RcmLike;
import com.atguigu.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author suprit
 * @since 2021-04-23
 */
public interface RcmLikeService extends IService<RcmLike> {

    ArrayList<UcenterMember> getAllUsers();

    ArrayList<EduCourse> getAllItems();

    ArrayList<EduCourse> findLikesItemsByUser(String id);
}
