package com.cos.service;

import com.cos.dao.UserTelCodeRepository;
import com.cos.domain.bean.UserTelCodeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/11/27 15:31
 * @Classname: UserTelCodeService
 * @To change this template use File | Settings | File Templates.
 */
@Service
public class UserTelCodeService {

    @Autowired
    private UserTelCodeRepository userTelCodeRepository;

    public UserTelCodeInfo create(UserTelCodeInfo info) {
        return userTelCodeRepository.save(info);
    }



}
