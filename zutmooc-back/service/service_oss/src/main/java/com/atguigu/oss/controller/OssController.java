package com.atguigu.oss.controller;

import com.atguigu.commonutils.R;
import com.atguigu.oss.service.impl.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//@CrossOrigin
@RestController
@RequestMapping("/eduoss/fileoss")
public class OssController {
    @Autowired
    private OssService ossService;

    //上传头像
    @PostMapping
    public R uploadFile(MultipartFile file){
        //返回上传到OSS的路径
        String url = ossService.uploadFile(file);
        return R.ok().data("url", url);
    }
}
