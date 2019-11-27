package com.cos.common.tools;

import lombok.experimental.UtilityClass;

import java.util.Optional;
import java.util.Random;
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

    private final static StringBuffer numbers = new StringBuffer("0123456789");

    /**
     * 验证手机号码
     *
     * @param phone
     * @return
     */
    public static boolean isMobilePhone(String phone) {
        if (!Optional.ofNullable(phone).isPresent()) {
            return false;
        }
        boolean flag = false;
        try {
            Pattern regex = Pattern
                    .compile("^(?:(?:\\+|00)86)?1(?:(?:3[\\d])|(?:4[5-7|9])|(?:5[0-3|5-9])|(?:6[5-7])|(?:7[0-8])|(?:8[\\d])|(?:9[1|8|9]))\\d{8}$");
//                    .compile("^(((1[3-8]))\\d{9})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
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
//            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            String check = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * @return
     * @desc 六位随机验证码
     */
    public static String getTelCode() {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        int range = numbers.length();
        for (int i = 1; i <= 6; i++) {
            sb.append(numbers.charAt(random.nextInt(range)));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(isMobilePhone("15269020596"));
        System.out.println(isEmail("15269020596@qq.com"));
    }
}
