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
 * @Date: @Date 2019/8/8 19:05
 * @Classname: UserImpInfo
 * @To change this template use File | Settings | File Templates.
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "user_imp_info")
public class UserImpInfo implements Serializable {

    @Id
    private Long id;
    private String sysCustomer;
    private Long userId;
    private String password;
    private int delFlag = 0;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
