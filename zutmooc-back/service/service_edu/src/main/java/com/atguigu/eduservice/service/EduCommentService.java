package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-05-04
 */
public interface EduCommentService extends IService<EduComment> {

    Map<String, Object> getCommentPage(String id, long current, long limit);
}
