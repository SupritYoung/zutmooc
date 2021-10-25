package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.eduservice.client.ComentClient;
import com.atguigu.eduservice.entity.EduComment;
import com.atguigu.eduservice.service.EduCommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-05-04
 */
@RestController
@RequestMapping("/eduservice/comment")
//@CrossOrigin
public class EduCommentController {
    @Autowired
    private EduCommentService commentService;

    @Autowired
    private ComentClient comentClient;
    //分页查询评论
    @ApiOperation("分页查询")
    @GetMapping("getCommentPage/{id}/{current}/{limit}")
    public R getCommentPage(@PathVariable String id, @PathVariable long current, @PathVariable long limit){
        Map<String,Object> mp = commentService.getCommentPage(id,current,limit);
        System.out.println(mp);
        return R.ok().data(mp);
    }
    //添加评论
    @ApiOperation("添加评论")
    @PostMapping("saveComment")
    public R save(@RequestBody EduComment eduComment, HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if(StringUtils.isEmpty(memberId)) {
            return R.error().message("请登录");
        }

        Map<String,Object> member = (Map<String, Object>) comentClient.getMemberInfoById(memberId).getData().get("userInfo");
        eduComment.setMemberId(memberId);
        eduComment.setNickname((String) member.get("nickname"));
        eduComment.setAvatar((String) member.get("avatar"));
        commentService.save(eduComment);
        return R.ok();
    }
    //通过删除评论
    @ApiOperation("删除评论")
    @DeleteMapping("delete/{id}")
    public R save(@PathVariable String id){
        commentService.removeById(id);
        return R.ok();
    }


}

