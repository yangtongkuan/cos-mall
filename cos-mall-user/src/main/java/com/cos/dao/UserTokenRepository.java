package com.cos.dao;

import com.cos.domain.bean.UserTokenInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.print.DocFlavor;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/11/5 21:20
 * @To change this template use File | Settings | File Templates.
 */
public interface UserTokenRepository extends MongoRepository<UserTokenInfo, Long> {

    UserTokenInfo findBySysCustomerAndTokenAndDelFlag(String sysCustomer, String token, int delFlag);

    List<UserTokenInfo> findBySysCustomerAndUserIdAndDelFlag(String sysCustomer, Long userId, int delFlag);

}
