package com.cos.service.sys;

import com.cos.domain.bean.sys.ParamsSettingInfo;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/11/27 15:44
 * @Classname: ParamsSettingService
 * @To change this template use File | Settings | File Templates.
 */
public interface ParamsSettingService {

    ParamsSettingInfo findByParam(String sysCustomer, String param);

}
