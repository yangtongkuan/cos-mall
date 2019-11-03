package com.cos.dao.system;

import com.cos.domain.bean.SysConstantSettingInfo;
import com.cos.domain.bean.SysCustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/11/2 16:53
 * @To change this template use File | Settings | File Templates.
 */
public interface SysCustomerRepository extends JpaRepository<SysConstantSettingInfo, Long> {

    List<SysCustomerInfo> findByDelFlag(int delFlag);

    // 获取客户端标识
    SysCustomerInfo findByIdentifyAndDelFlag(String identify, int delFlag);
}
