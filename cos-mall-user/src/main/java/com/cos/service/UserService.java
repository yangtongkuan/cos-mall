package com.cos.service;

import com.cos.common.tools.CosCommonUtils;
import com.cos.dao.UserRepository;
import com.cos.domain.bean.UserInfo;
import com.cos.domain.bean.UserTelCodeInfo;
import com.cos.domain.bean.sys.ParamsSettingInfo;
import com.cos.domain.utils.image.ImageUploadInfo;
import com.cos.domain.utils.sys.SysConstants;
import com.cos.domain.vo.UserInfoVo;
import com.cos.dto.user.UserInfoDto;
import com.cos.service.sys.ParamsSettingService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/10/22 22:05
 * @To change this template use File | Settings | File Templates.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTelCodeService userTelCodeService;
    @Autowired
    private ParamsSettingService paramsSettingService;

    @Autowired
    private ImageFileService imageFileService;

    public UserInfo getBySysCustomerAndUserId(String sysCustomer, Long userId) {
        if (userId == null) {
            return null;
        }
        UserInfo userInfo = userRepository.findBySysCustomerAndIdAndDelFlag(sysCustomer, userId, 0);
        return userInfo;
    }

    public UserInfo updateUserInfo(UserInfo userInfo, ImageUploadInfo image) {
        UserInfo userInfoDb = this.getBySysCustomerAndUserId(userInfo.getSysCustomer(), userInfo.getId());
        if (userInfoDb != null) {
            if (StringUtils.isNotEmpty(userInfo.getName())) {
                userInfoDb.setName(userInfo.getName());
            }
            if (StringUtils.isNotEmpty(userInfo.getProv())) {
                userInfoDb.setProv(userInfo.getProv());
            }
            if (StringUtils.isNotEmpty(userInfo.getCity())) {
                userInfoDb.setCity(userInfo.getCity());
            }
            if (StringUtils.isNotEmpty(userInfo.getCountry())) {
                userInfoDb.setCountry(userInfo.getCountry());
            }
            if (StringUtils.isNotEmpty(userInfo.getAddress())) {
                userInfoDb.setAddress(userInfo.getAddress());
            }
            if (StringUtils.isNotEmpty(userInfo.getSex())) {
                userInfoDb.setSex(userInfo.getSex());
            }
            if (StringUtils.isNotEmpty(userInfo.getSignature())) {
                userInfoDb.setSignature(userInfo.getSignature());
            }
            if (Optional.ofNullable(image).map(bean -> bean.getImage()).isPresent()) {
                imageFileService.uploadImageTest(image.getImage());
                userInfoDb.setPhoto("");
            }
            userInfoDb = userRepository.save(userInfoDb);
        }
        return userInfoDb;
    }

    public void sendEmailRegisterCode(String sysCustomer, String email) throws Exception {
        Integer count = userRepository.countBySysCustomerAndEmailAndDelFlag(sysCustomer, email, 0);
        if (count > 0) {
            throw new Exception("the current mailbox is registered");
        }
        String code = CosCommonUtils.getTelCode();
        UserTelCodeInfo codeInfo = new UserTelCodeInfo();

        ParamsSettingInfo param = paramsSettingService.findByParam(sysCustomer, SysConstants.TEL_CODE_EXPIRE);
        Integer expireMinute = 3;
        if (param != null && param.getParVal() != null) {
            expireMinute = Integer.parseInt(param.getParVal().trim());
        }
        Date date = new Date();
        codeInfo.setSysCustomer(sysCustomer)
                .setCodeType(UserTelCodeInfo.CODE_TYPE_REGISTER)
                .setEmail(email)
                .setDelFlag(0)
                .setCreateTime(date).setExpireDate(new Date(date.getTime() + expireMinute * 60 * 1000L));
        userTelCodeService.create(codeInfo);

        // 发送邮件验证码
        //

    }


}
