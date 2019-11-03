package com.cos.domain.util;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/8/11 16:41
 * @To 未查询到异常
 */
public class UnKnowSysCustomerException extends Exception {
    public UnKnowSysCustomerException() {
    }
    public UnKnowSysCustomerException(String message) {
        super(message);
    }
}
