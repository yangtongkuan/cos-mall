package com.cos.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.cos.dto.user.UserInfoDto;
import com.cos.service.UserService;
import com.cos.service.user.UserRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/10/22 21:41
 * @To change this template use File | Settings | File Templates.
 */
@Component
@Service(retries = 0, timeout = 3000)
public class UserRpcServiceImpl implements UserRpcService {
    @Autowired
    private UserService userService;

    @Override
    public UserInfoDto getBySysCustomerAndUserId(String sysCustomer, Long userId) {

        UserInfoDto dto = new UserInfoDto();
        dto.setSysCustomer(sysCustomer).setId(userId);
        return dto;
    }
}
