package com.cos.common.exception;

import com.cos.common.tools.AjaxResult;
import com.cos.domain.exception.UnKnowSysCustomerException;
import org.aspectj.weaver.AjAttribute;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/11/5 20:22
 * @To change this template use File | Settings | File Templates.
 * @desc : 全局异常处理
 */
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = UnKnowSysCustomerException.class)
    public Object globalExceptionHandler(Exception e) {
        return AjaxResult.errorResult(e.getMessage());

    }
}
