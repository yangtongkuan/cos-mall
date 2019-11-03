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
 * @Date: @Date 2019/8/11 16:10
 * @To change this template use File | Settings | File Templates.
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "sys_customer_info")
public class SysCustomerInfo implements Serializable {
    @Id
    @GeneratedValue
    private Long id;                        // id
    private String name;                    // 租户名
    private String identify;               // 系统标识
    private String pcDeployUrl;            //
    private int isLock;                    // 是否锁定
    private int delFlag = 0;               // 是否删除
    private String note;                    // 备注
    private String UUID;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
