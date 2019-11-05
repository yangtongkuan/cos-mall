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
 * @Date: @Date 2019/11/5 20:34
 * @To change this template use File | Settings | File Templates.
 */
@Data
@Accessors(chain = true)
@Document(collection = "user_token")
public class UserTokenInfo implements Serializable {

    private String id;                            // id
    private String sysCustomer;                 // 客户
    private String token;                        // token
    private Long userId;                         // 用户id
    private String loginIp;                      // 登录ip
    private Date loginTime;                     // 登录时间
    private Long expireDate = 7 * 24 * 60 * 60 * 1000L;// 默认超时7天
    private int delFlag;                        // 是否删除
}
