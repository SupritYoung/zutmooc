package com.atguigu.msmservice.utils;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {
    /**
     * @Method: createSimpleMail
     * @Description: 创建一封只包含文本的邮件
     */
    public static MimeMessage createSimpleMail(Session session, String email, String code) throws Exception {
        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // TODO 指明邮件的发件人
        message.setFrom(new InternetAddress("1123568899@qq.com"));
        // 指明邮件的收件人，发件人和收件人如果是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
        // 邮件的标题
        message.setSubject("【我要自学网】验证码");
        // 邮件的文本内容
        message.setContent("欢迎您注册【我要自学网】，账号注册验证码为:【"+code+"】（一分钟有效），请勿回复此邮箱", "text/html;charset=UTF-8");

        // 返回创建好的邮件对象
        return message;
    }
}
