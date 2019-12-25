package com.cos.web.controller;

import com.cos.common.tools.AjaxResult;
import com.cos.common.tools.CosCommonUtils;
import com.cos.domain.bean.UserInfo;
import com.cos.domain.bean.sys.SysCustomerInfo;
import com.cos.domain.utils.exception.UnKnowSysCustomerException;
import com.cos.domain.utils.image.ImageUploadInfo;
import com.cos.domain.vo.UserInfoVo;
import com.cos.service.UserAuthService;
import com.cos.service.UserService;
import com.cos.service.UserTokenService;
import com.cos.service.sys.SysCustomerService;
import org.apache.commons.lang.StringUtils;
import org.springframework.aop.aspectj.AbstractAspectJAdvice;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/11/26 21:17
 * @To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("user")
public class UserController {

    private SysCustomerService sysCustomerService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserTokenService userTokenService;

    // 1. 用户信息
    @RequestMapping(value = "/info/get", method = RequestMethod.POST)
    public String getUserInfo(String sysCustomer, Long userId) throws UnKnowSysCustomerException {
        SysCustomerInfo customerInfo = sysCustomerService.getValidSysCustomerInfo(sysCustomer);
        UserInfo userInfo = userService.getBySysCustomerAndUserId(customerInfo.getIdentify(), userId);
        if (userInfo == null) {
            return AjaxResult.errorResult("this is not exist");
        }
        UserInfoVo vo = new UserInfoVo();
        BeanUtils.copyProperties(userInfo, vo);
        return AjaxResult.successResult(vo);
    }

    @RequestMapping(value = "/info/modify", method = RequestMethod.POST)
    public String updateUserInfo(String token, UserInfo userInfo, ImageUploadInfo image) throws UnKnowSysCustomerException {
        SysCustomerInfo customerInfo = sysCustomerService.getValidSysCustomerInfo(userInfo.getSysCustomer());
        UserInfo userInfoDb = userService.getBySysCustomerAndUserId(customerInfo.getIdentify(), userTokenService.getUserIdByToken(userInfo.getSysCustomer(), token));
        if (userInfoDb == null) {
            return AjaxResult.errorResult("this is not exist");
        }
        userService.updateUserInfo(userInfo, image);
        return AjaxResult.successResult();
    }

    @RequestMapping(value = "/register/email/get-code", method = RequestMethod.POST)
    public String getUserInfo(String sysCustomer, String email) throws UnKnowSysCustomerException {
        SysCustomerInfo customerInfo = sysCustomerService.getValidSysCustomerInfo(sysCustomer);
        if (!CosCommonUtils.isEmail(email)) {
            return AjaxResult.errorResult("E-mail format is incorrect");
        }
        try {
            userService.sendEmailRegisterCode(customerInfo.getIdentify(), email);
        } catch (Exception e) {
            return AjaxResult.errorResult(e.getMessage());
        }
        return AjaxResult.successResult();
    }


}
