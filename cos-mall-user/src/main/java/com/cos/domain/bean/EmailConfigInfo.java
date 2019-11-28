package com.cos.domain.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/11/28 11:26
 * @Classname: EmailConfigInfo
 * @To change this template use File | Settings | File Templates.
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "email_config")
public class EmailConfigInfo implements Serializable {
    @Id
    @GeneratedValue
    private Long id;                    // id
    private String sysCustomer;         // 租户
    private String emailHost;           // 主机
    private String emailUsername;       // 登录账号
    private String emailPassword;       // 三方登录授权码
    private String senderFrom;          // 发送者账号
    private String encoding;            // 编码方式
    private String note;                // 备注
    private int delFlag = 0;            // 删除
    private String createUser;          // 创建者
    private Date createTime;            // 创建时间
    private String updateUser;          // 更新者
    private Date updateTime;            // 更新时间

}
