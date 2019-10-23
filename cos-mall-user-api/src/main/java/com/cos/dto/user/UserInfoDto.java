package com.cos.dto.user;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/10/22 17:58
 * @Classname: UserInfoDto
 * @To change this template use File | Settings | File Templates.
 */
@Data
@Accessors(chain = true)
public class UserInfoDto implements Serializable {
    private Long id;                // 用户id
    private String sysCustomer;    // 系统标识
    private String username;       //  用户账号
    private String name;            // 名字
    private String sex;             // sex boy girl
    private String prov;            // 省
    private String city;            // 市
    private String country;         // 区
    private String address;         // 地址
    private String phone;           // 登录账号
    private String photo;           // 头像
    private String signature;       // 签名
}
