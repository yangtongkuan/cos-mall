package com.cos.common.tools;

import lombok.experimental.UtilityClass;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/8/29 21:44
 * @Classname: CosCommonUtils
 * @To change this template use File | Settings | File Templates.
 */
@UtilityClass
public class CosCommonUtils {

    /**
     * 验证手机号码
     *
     * @param phone
     * @return
     */
    public static boolean isMobile(String phone) {
        if (!Optional.ofNullable(phone).isPresent()) {
            return false;
        }
        boolean flag = false;
        try {
            // /**
            // * 移动开头 ：134、135、136、137、138、139、 147 150、151、152、157、158、159
            // 181、182、183、187、188、189
            // * 联通开头：130、131、132、 155、156、 185、186
            // * 电信开头：133、 153、 180、189
            // */
            // 目前只检测13-18
            Pattern regex = Pattern
                    .compile("^(((1[3-8]))\\d{9})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
            Matcher matcher = regex.matcher(phone);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 验证邮箱格式
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (!Optional.ofNullable(email).isPresent()) {
            return false;
        }
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
}
