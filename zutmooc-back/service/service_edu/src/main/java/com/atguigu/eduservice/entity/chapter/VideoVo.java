package com.atguigu.eduservice.entity.chapter;

import lombok.Data;

//章节中的小节实体类，一个小节一个视频
@Data
public class VideoVo {

    private String id;

    private String title;

    private String videoSourceId;   //视频ID
}