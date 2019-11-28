package com.cos.dao;

import com.cos.domain.bean.EmailConfigInfo;
import com.cos.service.EmailConfigService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/11/28 11:46
 * @Classname: EmailConfigRepository
 * @To change this template use File | Settings | File Templates.
 */
public interface EmailConfigRepository extends JpaRepository<EmailConfigInfo, Long> {

    EmailConfigInfo findBySysCustomerAndDelFlag(String sysCustomer, int delFlag);

    List<EmailConfigInfo> findByDelFlag(int deFlag);

}
