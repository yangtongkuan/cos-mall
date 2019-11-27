package com.cos.domain.bean.sys;

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
 * @Date: @Date 2019/11/27 15:40
 * @Classname: ParamsSettingInfo
 * @To change this template use File | Settings | File Templates.
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "sys_constant_setting")
public class ParamsSettingInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    private String sysCustomer;
    private String param;
    private String parVal;
    private String note;
    private Date createTim;
    private String showPlate;
    private String valType;
    private String valDef;
    private String label;

}
