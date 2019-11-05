package com.cos.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/11/5 20:29
 * @To change this template use File | Settings | File Templates.
 */
@Data
@Accessors(chain = true)
public class UserInfoVo {
    private Long id;                // 用户id
    private String sysCustomer;    // 系统标识
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
