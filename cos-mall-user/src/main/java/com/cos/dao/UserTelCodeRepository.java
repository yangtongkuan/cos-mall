package com.cos.dao;

import com.cos.domain.bean.UserTelCodeInfo;
import com.cos.domain.bean.UserTokenInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/11/27 15:30
 * @Classname: UserTelCodeRepository
 * @To change this template use File | Settings | File Templates.
 */
public interface UserTelCodeRepository extends MongoRepository<UserTelCodeInfo, Long> {



}
