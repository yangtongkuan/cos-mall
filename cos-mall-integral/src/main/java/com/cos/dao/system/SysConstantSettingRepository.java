package com.cos.dao.system;

import com.cos.domain.bean.SysConstantSettingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/11/2 16:02
 * @To change this template use File | Settings | File Templates.
 * @des 系统参数
 */
public interface SysConstantSettingRepository extends JpaRepository<SysConstantSettingInfo, Long> {

    /**
     * @param sysCustomer
     * @param param
     * @param delFlag
     * @return
     * @desc 获取指定参数
     */
    SysConstantSettingInfo findBySysCustomerAndParamAndDelFlag(String sysCustomer, String param, int delFlag);

    List<SysConstantSettingInfo> findByDelFlag(int delFlag);

}
