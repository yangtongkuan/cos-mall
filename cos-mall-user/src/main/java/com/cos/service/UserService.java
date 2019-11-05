package com.cos.service;

import com.cos.dao.UserRepository;
import com.cos.domain.bean.UserInfo;
import com.cos.dto.user.UserInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/10/22 22:05
 * @To change this template use File | Settings | File Templates.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserInfo getBySysCustomerAndUserId(String sysCustomer, Long userId) {
        UserInfo userInfo = userRepository.findBySysCustomerAndIdAndDelFlag(sysCustomer, userId, 0);
        return userInfo;
    }
    

}
