package com.cos.common.tools;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/8/2 10:37
 * @Classname: AjaxResult
 * @To json 数据返回util
 */
public class AjaxResult {

    // 只会返回成功
    public static String successResult() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", 1);
        return jsonObject.toString();
    }

    public static String successResult(String info) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", 1);
        jsonObject.put("info", info);
        return jsonObject.toString();
    }

    public static String successResult(Object obj) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", 1);
        jsonObject.put("info", obj);
        return jsonObject.toString();
    }

    //成功。返回列表
    public static String successResult(int size, List<?> list) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", 1);
        jsonObject.put("size", size);
        jsonObject.put("data", list);
        return jsonObject.toString();
    }

    //成功。返回列表
    public static String successResult(List<?> list, int totalCount) {
        JSONObject jsonObject = new JSONObject();
        if (!Optional.ofNullable(list).isPresent()) {
            list = Collections.emptyList();
        }
        jsonObject.put("status", 1);
        jsonObject.put("totalCount", totalCount);
        jsonObject.put("size", list.size());
        jsonObject.put("data", list);
        return jsonObject.toString();
    }

    //错误，返回错误提示
    public static String errorResult(String info) {
        return errorResult(info, null);
    }

    //错误，返回错误提示
    public static String errorResult(String info, Exception e) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", 0);
        jsonObject.put("type", "comError");
        if (e != null) {
            jsonObject.put("msg", e.getMessage());
        }
        jsonObject.put("error", info);
        return jsonObject.toString();
    }

    public static String errorResult(String info, String errorType, Exception e) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", 0);
        if (StringUtils.isNotEmpty(errorType)) {
            jsonObject.put("type", errorType);
        } else {
            jsonObject.put("type", "comError");
        }
        if (e != null) {
            jsonObject.put("msg", e.getMessage());
        }
        jsonObject.put("error", info);
        return jsonObject.toString();
    }

    // 登录失效
    public static String errorLogoutResult() {
        return errorResult("当前帐号失效，请重新登录", "notLogin", null);
    }

    // 没有操作权限
    public static String errorAuthResult() {
        return errorResult("您没有操作权限", "noAuth", null);
    }

    // 没有操作权限
    public static String errorBanIpResult(String... ip) {
        if (ip != null && ip.length > 0) {
            return errorResult("你的ip" + ip[0] + "被禁止访问,请联系客服", "banIp", null);
        } else {
            return errorResult("你的ip被禁止访问,请联系客服", "banIp", null);
        }
    }


}
