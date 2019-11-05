package com.cos.dao;

import com.cos.domain.bean.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/11/5 21:12
 * @To change this template use File | Settings | File Templates.
 */
public interface UserRepository extends JpaRepository<UserInfo, Long> {

    UserInfo findBySysCustomerAndIdAndDelFlag(String sysCustomer, Long id, int delFlag);

}
