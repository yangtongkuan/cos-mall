package com.cos.web.controller;


import com.cos.dto.user.UserInfoDto;
import com.cos.service.user.UserRpcService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/10/23 22:21
 * @To change this template use File | Settings | File Templates.
 */
@Service
public class TestService {

    @Reference(timeout = 3000)
    private UserRpcService userRpcService;

    public UserInfoDto getUserInfoTo() {
        return userRpcService.getBySysCustomerAndUserId("system", 1L);
    }
}
