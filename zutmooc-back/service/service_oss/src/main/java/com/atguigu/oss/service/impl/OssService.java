package com.atguigu.oss.service.impl;

import org.springframework.web.multipart.MultipartFile;

public interface OssService{
    String uploadFile(MultipartFile file);
}
