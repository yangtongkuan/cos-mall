package com.cos.service.sys;

import com.cos.domain.bean.sys.SysCustomerInfo;
import com.cos.domain.utils.exception.UnKnowSysCustomerException;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/11/20 18:10
 * @Classname: SysCustomerService
 * @To change this template use File | Settings | File Templates.
 */
public interface SysCustomerService {

    SysCustomerInfo get(String sysCustomer);

    SysCustomerInfo getValidSysCustomerInfo(String sysCustomer) throws UnKnowSysCustomerException;


}


