package com.cos.web.controller;

import com.cos.dto.user.UserInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.midi.Soundbank;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/10/23 22:21
 * @To change this template use File | Settings | File Templates.
 */
@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping("/test")
    public Object test() {
        UserInfoDto infoTo = testService.getUserInfoTo();
        if (infoTo == null) {
            System.out.println(infoTo.toString());
        }
        return infoTo;
    }

}
