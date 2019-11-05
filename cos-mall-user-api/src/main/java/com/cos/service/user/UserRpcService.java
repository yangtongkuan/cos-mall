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
    /**
     * @param sysCustomer
     * @param userId
     * @return
     * @desc 根据用户的id获取用户的信息
     */
    public UserInfoDto getBySysCustomerAndUserId(String sysCustomer, Long userId);

    /**
     * @param sysCustomer
     * @param token
     * @return
     * @desc 根据token 获取用户信息
     */
    public UserInfoDto getBySysCustomerAndToken(String sysCustomer, String token);
}
