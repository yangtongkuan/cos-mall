package com.cos.dao;

import com.cos.domain.bean.UserImpInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/8/29 21:20
 * @Classname: UserImpRepository
 * @To change this template use File | Settings | File Templates.
 */
public interface UserImpRepository extends JpaRepository<UserImpInfo, Long> {

    UserImpInfo findBySysCustomerAndIdAndDelFlag(String sysCustomer, Long userId, int delFlag);
}
