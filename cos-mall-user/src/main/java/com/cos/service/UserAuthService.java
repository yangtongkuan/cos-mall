package com.cos.service;

import com.cos.dao.UserRepository;
import com.cos.domain.bean.UserInfo;
import com.cos.domain.vo.UserInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/11/7 22:37
 * @To change this template use File | Settings | File Templates.
 * @desc 用户认证
 */
@Service
public class UserAuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserImpService userImpService;
    @Autowired
    private UserTokenService userTokenService;

    public UserInfoVo authByPhone(String sysCustomer, String phone, String passWd, String loginIp) throws Exception {
        UserInfo userInfo = userRepository.findBySysCustomerAndPhoneAndDelFlag(sysCustomer, phone, 0);
        if (userInfo == null) {
            throw new Exception("用户不存在");
        }
        // 密码
        userImpService.validUserPassWd(sysCustomer, userInfo.getId(), passWd);
        UserInfoVo vo = new UserInfoVo();
        BeanUtils.copyProperties(userInfo, vo);
        String token = userTokenService.createUserToken(sysCustomer, userInfo.getId(), loginIp);
        vo.setToken(token);
        return vo;
    }
}
