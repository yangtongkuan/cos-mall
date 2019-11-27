package com.cos.domain.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/10/22 22:10
 * @To change this template use File | Settings | File Templates.
 */
@Table(name = "user_info")
@Entity
@Data
@Accessors(chain = true)
public class UserInfo implements Serializable {
    @Id
    private Long id;                // 用户id
    private String sysCustomer;    // 系统标识
    private String name;            // 名字
    private String sex;             // sex boy girl
    private String prov;            // 省
    private String city;            // 市
    private String country;         // 区
    private String address;         // 地址
    private String phone;           // 登录账号
    private String email;           // 邮箱
    private String photo;           // 头像
    private String signature;       // 签名
    private int delFlag = 0;        // 是否删除
    private Date createTime;        // 创建时间
    private Long createId;          // 创者者
    private Long updateId;          // 更新者
    private Date updateTime;        // 更新时间
}
