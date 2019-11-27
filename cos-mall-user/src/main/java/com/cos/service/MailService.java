package com.cos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/11/27 18:17
 * @Classname: MailService
 * @To change this template use File | Settings | File Templates.
 */
@Service
public class MailService {

    @Autowired
    private JavaMailSenderImpl mailSender;//注入邮件工具类
    /**
     * 发送邮件
     */
    public void sendMail() {
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true);//true表示支持复杂类型
            messageHelper.setFrom("cosyangtongkuan@163.com");//邮件发信人
            messageHelper.setTo("yangtongkuan@qq.com");//邮件收信人
            messageHelper.setSubject("测试");//邮件主题
            messageHelper.setText("啦啦啦啦");//邮件内容
            mailSender.send(messageHelper.getMimeMessage());//正式发送邮件
        } catch (Exception e) {
            throw new RuntimeException(e);//发送失败
        }
    }
}
