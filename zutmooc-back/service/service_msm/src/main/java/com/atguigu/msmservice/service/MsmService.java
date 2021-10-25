package com.atguigu.msmservice.service;

import java.util.Map;

public interface MsmService {
    //发送短信的方法
    boolean send(Map<String, Object> param, String phone);

    //发送邮件的方法
    boolean sendMail(Map<String, Object> param, String email, String code) throws Exception;
}
