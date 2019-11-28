package com.cos.service.sys;

import com.cos.dao.sys.ParamsSettingRepository;
import com.cos.domain.bean.sys.ParamsSettingInfo;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/11/27 15:44
 * @Classname: ParamsSettingServiceImpl
 * @To change this template use File | Settings | File Templates.
 */
//@Service
//@ConditionalOnMissingBean(ParamsSettingService.class)
public class ParamsSettingServiceImpl implements ParamsSettingService {
    @Autowired
    private ParamsSettingRepository paramsSettingRepository;

    @Override
    public ParamsSettingInfo findByParam(String sysCustomer, String param) {
        return paramsSettingRepository.findBySysCustomerAndParam(sysCustomer, param);
    }
}
