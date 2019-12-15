package com.cos.web.controller;

import com.cos.common.tools.AjaxResult;
import com.cos.common.tools.ClientIpUtils;
import com.cos.domain.bean.sys.SysCustomerInfo;
import com.cos.domain.utils.exception.UnKnowSysCustomerException;
import com.cos.domain.vo.UserInfoVo;
import com.cos.service.UserAuthService;
import com.cos.service.UserService;
import com.cos.service.sys.SysCustomerService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/11/7 22:27
 * @To change this template use File | Settings | File Templates.
 * @desc 用户登录
 */
@RestController
public class UserAuthController {

    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private SysCustomerService sysCustomerService;

    @RequestMapping("/mobile/login/by/phone")
    public String authByPhone(String sysCustomer, String username, String password, HttpServletRequest request) throws UnKnowSysCustomerException {
        SysCustomerInfo customerInfo = sysCustomerService.getValidSysCustomerInfo(sysCustomer);
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return AjaxResult.errorResult("用户名或者密码不能为空");
        }
        String ip = ClientIpUtils.getClientIp(request);
        UserInfoVo vo = null;
        try {
            vo = userAuthService.authByPhone(customerInfo.getIdentify(), username, password, ip);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.errorResult(e.getMessage());
        }
        return AjaxResult.successResult(vo);
    }
}
