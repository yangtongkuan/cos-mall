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
            messageHelper.setFrom("cosyangtongkuan@163.com", "cos");//邮件发信人
            messageHelper.setTo("1061203621@qq.com");//邮件收信人
            messageHelper.setSubject("cos注册验证码");//邮件主题
//            String text = "尊敬的用户,您好:\n"
//                    + "\n本次请求的邮件验证码为:${telCode},本验证码5分钟内有效，请及时输入。（请勿泄露此验证码）\n"
//                    + "\n如非本人操作，请忽略该邮件。\n(这是一封自动发送的邮件，请不要直接回复）";
//            String key = "telCode";
//            String replace = "${" + key + "}";
//            text = text.replace(replace, "6666");
            String text = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                    "    <title></title>\n" +
                    "    <meta charset=\"utf-8\" />\n" +
                    "\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <div class=\"qmbox qm_con_body_content qqmail_webmail_only\" id=\"mailContentContainer\" style=\"\">\n" +
                    "        <style type=\"text/css\">\n" +
                    "            .qmbox body {\n" +
                    "                margin: 0;\n" +
                    "                padding: 0;\n" +
                    "                background: #fff;\n" +
                    "                font-family: \"Verdana, Arial, Helvetica, sans-serif\";\n" +
                    "                font-size: 14px;\n" +
                    "                line-height: 24px;\n" +
                    "            }\n" +
                    "\n" +
                    "            .qmbox div, .qmbox p, .qmbox span, .qmbox img {\n" +
                    "                margin: 0;\n" +
                    "                padding: 0;\n" +
                    "            }\n" +
                    "\n" +
                    "            .qmbox img {\n" +
                    "                border: none;\n" +
                    "            }\n" +
                    "\n" +
                    "            .qmbox .contaner {\n" +
                    "                margin: 0 auto;\n" +
                    "            }\n" +
                    "\n" +
                    "            .qmbox .title {\n" +
                    "                margin: 0 auto;\n" +
                    "                background: url() #CCC repeat-x;\n" +
                    "                height: 30px;\n" +
                    "                text-align: center;\n" +
                    "                font-weight: bold;\n" +
                    "                padding-top: 12px;\n" +
                    "                font-size: 16px;\n" +
                    "            }\n" +
                    "\n" +
                    "            .qmbox .content {\n" +
                    "                margin: 4px;\n" +
                    "            }\n" +
                    "\n" +
                    "            .qmbox .biaoti {\n" +
                    "                padding: 6px;\n" +
                    "                color: #000;\n" +
                    "            }\n" +
                    "\n" +
                    "            .qmbox .xtop, .qmbox .xbottom {\n" +
                    "                display: block;\n" +
                    "                font-size: 1px;\n" +
                    "            }\n" +
                    "\n" +
                    "            .qmbox .xb1, .qmbox .xb2, .qmbox .xb3, .qmbox .xb4 {\n" +
                    "                display: block;\n" +
                    "                overflow: hidden;\n" +
                    "            }\n" +
                    "\n" +
                    "            .qmbox .xb1, .qmbox .xb2, .qmbox .xb3 {\n" +
                    "                height: 1px;\n" +
                    "            }\n" +
                    "\n" +
                    "            .qmbox .xb2, .qmbox .xb3, .qmbox .xb4 {\n" +
                    "                border-left: 1px solid #BCBCBC;\n" +
                    "                border-right: 1px solid #BCBCBC;\n" +
                    "            }\n" +
                    "\n" +
                    "            .qmbox .xb1 {\n" +
                    "                margin: 0 5px;\n" +
                    "                background: #BCBCBC;\n" +
                    "            }\n" +
                    "\n" +
                    "            .qmbox .xb2 {\n" +
                    "                margin: 0 3px;\n" +
                    "                border-width: 0 2px;\n" +
                    "            }\n" +
                    "\n" +
                    "            .qmbox .xb3 {\n" +
                    "                margin: 0 2px;\n" +
                    "            }\n" +
                    "\n" +
                    "            .qmbox .xb4 {\n" +
                    "                height: 2px;\n" +
                    "                margin: 0 1px;\n" +
                    "            }\n" +
                    "\n" +
                    "            .qmbox .xboxcontent {\n" +
                    "                display: block;\n" +
                    "                border: 0 solid #BCBCBC;\n" +
                    "                border-width: 0 1px;\n" +
                    "            }\n" +
                    "\n" +
                    "            .qmbox .line {\n" +
                    "                margin-top: 6px;\n" +
                    "                border-top: 1px dashed #B9B9B9;\n" +
                    "                padding: 4px;\n" +
                    "            }\n" +
                    "\n" +
                    "            .qmbox .neirong {\n" +
                    "                padding: 6px;\n" +
                    "                color: #666666;\n" +
                    "            }\n" +
                    "\n" +
                    "            .qmbox .foot {\n" +
                    "                padding: 6px;\n" +
                    "                color: #777;\n" +
                    "            }\n" +
                    "\n" +
                    "            .qmbox .font_darkblue {\n" +
                    "                color: #006699;\n" +
                    "                font-weight: bold;\n" +
                    "            }\n" +
                    "\n" +
                    "            .qmbox .font_lightblue {\n" +
                    "                color: #008BD1;\n" +
                    "                font-weight: bold;\n" +
                    "            }\n" +
                    "\n" +
                    "            .qmbox .font_gray {\n" +
                    "                color: #888;\n" +
                    "                font-size: 12px;\n" +
                    "            }\n" +
                    "        </style>\n" +
                    "        <div class=\"contaner\">\n" +
                    "            <div class=\"title\">$(title)</div>\n" +
                    "            <div class=\"content\">\n" +
                    "                <p class=\"biaoti\"><b>亲爱的用户，你好！</b></p>\n" +
                    "                <b class=\"xtop\"><b class=\"xb1\"></b><b class=\"xb2\"></b><b class=\"xb3\"></b><b class=\"xb4\"></b></b>\n" +
                    "                <div class=\"xboxcontent\">\n" +
                    "                    <div class=\"neirong\">\n" +
                    "                        <p><b>请核对你的用户名：</b><span id=\"userName\" class=\"font_darkblue\">$(userName)</span></p>\n" +
                    "                        <p><b>$(type)的验证码：</b><span class=\"font_lightblue\"><span id=\"yzm\" data=\"$(captcha)\" onclick=\"return false;\" t=\"7\" style=\"border-bottom: 1px dashed rgb(204, 204, 204); z-index: 1; position: static;\">$(captcha)</span></span><br><span class=\"font_gray\">(请输入该验证码完成$(type)，验证码30分钟内有效！)</span></p>\n" +
                    "                        <div class=\"line\">如果你未申请$(type)服务，请忽略该邮件。</div>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "                <b class=\"xbottom\"><b class=\"xb4\"></b><b class=\"xb3\"></b><b class=\"xb2\"></b><b class=\"xb1\"></b></b>\n" +
                    "                <p class=\"foot\">如果仍有问题，请拨打我们的会员服务专线: <span data=\"800-820-5100\" onclick=\"return false;\" t=\"7\" style=\"border-bottom: 1px dashed rgb(204, 204, 204); z-index: 1; position: static;\">021-51875288\n" +
                    "</span></p>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "        <style type=\"text/css\">\n" +
                    "            .qmbox style, .qmbox script, .qmbox head, .qmbox link, .qmbox meta {\n" +
                    "                display: none !important;\n" +
                    "            }\n" +
                    "        </style>\n" +
                    "    </div>\n" +
                    "</body>\n" +
                    "</html>\n";
            messageHelper.setText(text, true);    //设置邮件正文
//            messageHelper.setText("啦啦啦啦");//邮件内容
            mailSender.send(messageHelper.getMimeMessage());//正式发送邮件
        } catch (Exception e) {
            throw new RuntimeException(e);//发送失败
        }
        System.out.println(DateUtils.getCurrentTimeMillis());
    }


}
