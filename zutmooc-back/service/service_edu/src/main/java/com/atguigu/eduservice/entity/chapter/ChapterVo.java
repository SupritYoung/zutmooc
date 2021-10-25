package com.atguigu.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//章节实体类
@Data
public class ChapterVo {

    private String id;

    private String title;

    //一个章节包含多个小节
    private List<VideoVo> children = new ArrayList<>();
}
