package com.cos.domain.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/11/2 15:58
 * @To change this template use File | Settings | File Templates.
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "sys_constant_setting")
public class SysConstantSettingInfo {
    @Id
    @GeneratedValue
    private Long id;
    private String sysCustomer;
    private String param;
    private String parVal;
    private String note;
    private String uuId;
    private int delFlag;
    private Date createTime;
}
