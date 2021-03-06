package com.cos.service;

import com.cos.common.config.redis.RedisCacheUtils;
import com.cos.common.tools.DateUtils;
import com.cos.common.tools.IdWorker;
import com.cos.common.tools.RandomCharsUtils;
import com.cos.dao.UserTokenRepository;
import com.cos.domain.bean.UserInfo;
import com.cos.domain.bean.UserTokenInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/11/5 20:33
 * @To change this template use File | Settings | File Templates.
 * @desc 用户token生成记录
 */
@Service
public class UserTokenService {

    private static final Long expireTime = 30 * 24 * 60 * 60 * 1000L;
    private static final int TOKEN_LENGTH = 20;

    @Autowired
    private RedisCacheUtils redisCacheUtils;
    @Autowired
    private UserTokenRepository userTokenRepository;

    public Long getUserIdByToken(String sysCustomer, String token) {
        UserTokenInfo userToken = userTokenRepository.findBySysCustomerAndTokenAndDelFlag(sysCustomer, token, 0);
        if (userToken == null) {
            return null;
        }
        if (DateUtils.getCurrentTimeMillis() - userToken.getLoginTime().getTime() > userToken.getExpireDate()) {
            this.removeTokenCache(userToken);
            return null;
        }
        return userToken.getUserId();
    }

    public void removeTokenCache(UserTokenInfo userToken) {
        if (userToken != null) {
            userToken.setDelFlag(1);
            userTokenRepository.save(userToken);
            redisCacheUtils.del(getKey(userToken.getSysCustomer(), userToken.getToken()));
        }
    }

    public void removeTokenCache(String sysCustomer, Long userId) {
        List<UserTokenInfo> userTokenList = userTokenRepository.findBySysCustomerAndUserIdAndDelFlag(sysCustomer, userId, 0);
        if (userTokenList != null && !userTokenList.isEmpty()) {
            userTokenList = userTokenList.stream().map(bean -> bean.setDelFlag(1)).collect(Collectors.toList());
            userTokenRepository.saveAll(userTokenList);
            List<String> list = userTokenList.stream().map(bean -> {
                return getKey(bean.getSysCustomer(), bean.getToken());
            }).collect(Collectors.toList());
//            redisCacheUtils.del(list.toArray(new String[list.size()]));
        }
    }

    // 添加一个token
    public String createUserToken(String sysCustomer, Long userId, String loginIp) {
        this.removeTokenCache(sysCustomer, userId);
        String token = createToken();
        UserTokenInfo info = new UserTokenInfo();
        info.setUserId(userId).setSysCustomer(sysCustomer).setDelFlag(0).setLoginIp(loginIp)
                .setToken(token).setLoginTime(new Date()).setExpireDate(expireTime);
        userTokenRepository.save(info);
//        redisCacheUtils.set(getKey(sysCustomer, token), userId, expireTime / 1000, TimeUnit.SECONDS);
        return token;
    }

    private String getKey(String sysCustomer, String token) {
        return "token:" + sysCustomer + ":" + token;
    }

    private static String createToken() {
        long nextId = IdWorker.getInstance().nextId();
        return RandomCharsUtils.getRandomChar(nextId + "", TOKEN_LENGTH);
    }

}
