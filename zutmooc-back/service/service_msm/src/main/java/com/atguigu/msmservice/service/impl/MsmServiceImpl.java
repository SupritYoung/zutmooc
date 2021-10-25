package com.atguigu.msmservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.atguigu.msmservice.service.MsmService;
import com.atguigu.msmservice.utils.MailUtil;
import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.Properties;

@Service
public class MsmServiceImpl implements MsmService {


    //发送短信的方法
    @Override
    public boolean send(Map<String, Object> param, String phone) {
        if(StringUtils.isEmpty(phone)) return false;

        DefaultProfile profile =
                DefaultProfile.getProfile("default", "LTAI5tQo5uaxSXXXa3AyUQKF", "NLmaV2tOUzf48ykGFVsGEUKr8GlWgg");
        IAcsClient client = new DefaultAcsClient(profile);

        //设置相关固定的参数
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        //设置发送相关的参数
        request.putQueryParameter("PhoneNumbers",phone); //手机号
        request.putQueryParameter("SignName","我的谷粒在线教育网站"); //申请阿里云 签名名称
        request.putQueryParameter("TemplateCode","SMS_180051135"); //申请阿里云 模板code
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param)); //核心验证码数据，转换json数据传递

        try {
            //最终发送
            CommonResponse response = client.getCommonResponse(request);
            boolean success = response.getHttpResponse().isSuccess();
            return success;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //发送邮件的方法
    @Override
    public boolean sendMail(Map<String, Object> param, String email, String code) throws Exception {
        Properties prop = new Properties();
        // 开启debug调试，以便在控制台查看
        prop.setProperty("mail.debug", "true");
        // 设置邮件服务器主机名
        prop.setProperty("mail.host", "smtp.qq.com");
        // 发送服务器需要身份验证
        prop.setProperty("mail.smtp.auth", "true");
        // 发送邮件协议名称
        prop.setProperty("mail.transport.protocol", "smtp");
        // 开启SSL加密，否则会失败
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);
        // 创建session
        Session session = Session.getInstance(prop);
        // 通过session得到transport对象
        Transport ts = session.getTransport();
        // 连接邮件服务器：邮箱类型，帐号，POP3/SMTP协议授权码 163使用：smtp.163.com
        // TODO QQ号和授权码改成你自己的
        ts.connect("smtp.qq.com", "1123568899", "bprfufjvuqffiibc");
        // 创建邮件
        Message message = MailUtil.createSimpleMail(session, email, code);
        try {
            // 发送邮件
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
        }catch (Exception exception){
            exception.printStackTrace();
            return false;
        }
        return true;
    }
}
