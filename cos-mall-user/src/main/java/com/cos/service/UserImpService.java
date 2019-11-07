package com.cos.service;

import com.cos.common.tools.MD5Utils;
import com.cos.dao.UserImpRepository;
import com.cos.domain.bean.UserImpInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/8/29 21:21
 * @Classname: UserImpService
 * @To change this template use File | Settings | File Templates.
 */
@Service
public class UserImpService {

    @Autowired
    private UserImpRepository userImpRepository;

    public void validUserPassWd(String sysCustomer, Long userId, String passWd) throws Exception {
        UserImpInfo userImp = userImpRepository.findBySysCustomerAndIdAndDelFlag(sysCustomer, userId, 0);
        if (userImp == null) {
            throw new Exception("密码存在异常,请联系客服");
        }
        String byMd5 = MD5Utils.encoderByMd5(passWd);
        if (!byMd5.equals(userImp.getPassword())) {
            throw new Exception("密码错误,请重新输入");
        }
    }


}
