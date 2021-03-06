package com.cos.web.controller;

import com.cos.common.config.redis.RedisCacheUtils;
import com.cos.common.tools.AjaxResult;
import com.cos.dto.user.UserInfoDto;
import com.cos.service.EmailSendService;
import com.cos.service.ImageFileService;
import com.cos.service.MailService;
import com.cos.service.sys.SysCustomerService;
import com.cos.service.sys.impl.SysCustomerServiceDefaultImpl;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/10/31 22:49
 * @To change this template use File | Settings | File Templates.
 */
@RestController
public class UploadTestController {
    @Autowired
    private ImageFileService imageFileService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisCacheUtils redisCacheUtils;

    //    @Autowired
//    private MailService mailService;
    @Autowired
    private EmailSendService emailSendService;

    @RequestMapping("/image/upload/test")
    public Object uploadImage(MultipartFile file) {
        imageFileService.uploadImageTest(file);
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setId(1L);
        redisCacheUtils.set("ad", userInfoDto, 100L, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set("user:1", userInfoDto);
        redisTemplate.opsForValue().set("test:1", userInfoDto);
        UserInfoDto redis_dto = (UserInfoDto) redisTemplate.opsForValue().get("user:1");
        return AjaxResult.successResult(redis_dto);
    }

    @RequestMapping("test")
    public String test() {
//        mailService.sendMail();
        SysCustomerService service = new SysCustomerServiceDefaultImpl();
//        emailSendService.sendEmail("cos-mall");
        return AjaxResult.successResult();
    }

}
