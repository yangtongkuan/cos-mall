package com.cos.dao.sys;

import com.cos.domain.bean.sys.SysCustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/8/11 16:18
 * @To change this template use File | Settings | File Templates.
 */
public interface SysCustomerRepository extends JpaRepository<SysCustomerInfo, Long> {

    // 获取所有的客户端
    List<SysCustomerInfo> findByDelFlag(int delFlag);

    // 获取客户端标识
    SysCustomerInfo findByIdentifyAndDelFlag(String identify, int delFlag);


}
