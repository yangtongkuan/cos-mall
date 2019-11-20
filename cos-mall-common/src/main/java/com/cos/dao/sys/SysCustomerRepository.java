package com.cos.dao.sys;

import com.cos.domain.bean.sys.SysCustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/11/20 18:08
 * @Classname: SysCustomerRepository
 * @To change this template use File | Settings | File Templates.
 */
public interface SysCustomerRepository extends JpaRepository<SysCustomerInfo, Long> {
}
