package com.atguigu.edurecommend.controller;


import com.atguigu.commonutils.R;
import com.atguigu.edurecommend.utils.Recommendation;
import com.atguigu.eduservice.entity.EduCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author suprit
 * @since 2021-04-23
 */
//@CrossOrigin
@RestController
@RequestMapping("/recommend")
public class RcmLikeController {
    @Autowired
    private Recommendation recommendation;

    @GetMapping("/getRecommend/{id}")
    public R getRecommend(@PathVariable String id) {
        List<EduCourse> list = recommendation.getRecommend(id);
        if(list == null)
            return R.error().message("生成个性化推荐失败");
        else
            return R.ok().data("list",list);
    }
}

