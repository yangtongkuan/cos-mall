package com.cos.dao.sys;

import com.cos.domain.bean.sys.ParamsSettingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/11/27 15:43
 * @Classname: ParamsSettingRepository
 * @To change this template use File | Settings | File Templates.
 */
public interface ParamsSettingRepository extends JpaRepository<ParamsSettingInfo, Long> {

    ParamsSettingInfo findBySysCustomerAndParam(String sysCustomer, String param);
}
