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
 * @Date: @Date 2019/8/11 16:10
 * @To change this template use File | Settings | File Templates.
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "t_sys_customer_info")
public class SysCustomerInfo implements Serializable {
    @Id
    private Long id;
    private String name;
    private String identify;
    private String pcDeployUrl;
    private int delFlag = 0;
    private String note;
    private String UUID;
    private Date createTime;
    private Integer fVersion;
}
