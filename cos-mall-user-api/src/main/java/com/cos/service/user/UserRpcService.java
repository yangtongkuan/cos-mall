package com.cos.service.user;

import com.cos.dto.user.UserInfoDto;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/10/22 17:56
 * @Classname: UserRpcService
 * @To change this template use File | Settings | File Templates.
 */
public interface UserRpcService {
    public UserInfoDto getBySysCustomerAndUserId(String sysCustomer, Long userId);
}
