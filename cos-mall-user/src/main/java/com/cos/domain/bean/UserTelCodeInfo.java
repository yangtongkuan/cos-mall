package com.cos.domain.bean;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/11/27
 * @Classname: UserTelCodeInfo
 * @To change this template use File | Settings | File Templates.
 */
@Data
@Accessors(chain = true)
@Document(collection = "user_tel_code")
public class UserTelCodeInfo implements Serializable {

    public static final String CODE_TYPE_REGISTER = "code_type_register";

    private String id;                            // id
    private String sysCustomer;                   // 客户
    private String codeType;                      // code 类型
    private Long userId;                          // 用户id
    private String phone;                         // 用户的账号
    private String email;                         // 用户email
    private Date createTime;                      // 创建时间
    private Date expireDate;                      // 有效市场
    private int delFlag;                          // 是否删除
}
