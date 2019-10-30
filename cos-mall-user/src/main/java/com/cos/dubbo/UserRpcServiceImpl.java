package com.cos.dubbo;

import com.cos.dto.user.UserInfoDto;
import com.cos.service.UserService;
import com.cos.service.user.UserRpcService;
import org.apache.dubbo.config.annotation.Service;
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
@Service
public class UserRpcServiceImpl implements UserRpcService {
    @Override
    public UserInfoDto getBySysCustomerAndUserId(String sysCustomer, Long userId) {

        UserInfoDto dto = new UserInfoDto();
        dto.setSysCustomer(sysCustomer).setId(userId);
        return dto;
    }
}
