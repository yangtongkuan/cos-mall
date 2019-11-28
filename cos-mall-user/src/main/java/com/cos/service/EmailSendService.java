package com.cos.service;

import com.cos.common.tools.DateUtils;
import com.cos.domain.bean.EmailConfigInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/11/28 12:02
 * @Classname: EmailSendService
 * @To change this template use File | Settings | File Templates.
 */
@Service
public class EmailSendService {

    private ConcurrentHashMap<String, JavaMailSenderImpl> dataMap = new ConcurrentHashMap<>();

    @Autowired
    private EmailConfigService emailConfigService;

    private JavaMailSender getJavaMailSender(String sysCustomer) throws Exception {
        JavaMailSenderImpl mailSender = dataMap.get(sysCustomer);
        if (mailSender == null) {
            EmailConfigInfo configInfo = emailConfigService.getBySysCustomer(sysCustomer);
            if (configInfo == null) {
                throw new Exception("the  email config is not exist");
            }
            mailSender = new JavaMailSenderImpl();
            mailSender.setDefaultEncoding(configInfo.getEncoding());
            mailSender.setHost(configInfo.getEmailHost());
            mailSender.setUsername(configInfo.getEmailUsername());
            mailSender.setPassword(configInfo.getEmailPassword());
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", true);
            properties.put("mail.smtp.timeout", 25000);
            properties.put("mail.smtp.from", configInfo.getSenderFrom());
            mailSender.setJavaMailProperties(properties);
            dataMap.put(sysCustomer, mailSender);
        }
        return mailSender;
    }

    @Async
    public void sendEmail(String sysCustomer) {
        System.out.println(DateUtils.getCurrentTimeMillis());
        try {
            JavaMailSender mailSender = this.getJavaMailSender(sysCustomer);
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true);//true表示支持复杂类型
            messageHelper.setFrom("cosyangtongkuan@163.com");//邮件发信人
            messageHelper.setTo("yangtongkuan@qq.com");//邮件收信人
            messageHelper.setSubject("测试");//邮件主题
            messageHelper.setText("啦啦啦啦");//邮件内容
            mailSender.send(messageHelper.getMimeMessage());//正式发送邮件
        } catch (Exception e) {
            throw new RuntimeException(e);//发送失败
        }
        System.out.println(DateUtils.getCurrentTimeMillis());
    }


}
