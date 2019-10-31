package com.cos.common.tools;

import org.apache.commons.lang.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/8/5 9:03
 * @Classname: MD5Utils
 * @To 密码加密算法
 */
public class MD5Utils {

    // 十六进制下数字到字符的映射数组
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static String generatePassword(String inputString) throws NoSuchAlgorithmException {
        return encoderByMd5(inputString);
    }

    // md5进行加密
    public static synchronized String encoderByMd5(String originString) throws NoSuchAlgorithmException {
        if (StringUtils.isNotEmpty(originString)) {
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
            byte[] results = md.digest(originString.getBytes());
            // 将得到的字节数组变成字符串返回
            String resultString = byteArrayToHexString(results);
            return resultString.toUpperCase();
        } else {
            throw new IllegalArgumentException("加密报文不能为空");
        }
    }
    public static String encoderByMd5(String origin, String charset) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (StringUtils.isNotEmpty(charset))
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charset)));
        } catch (Exception exception) {
        }
        return resultString;
    }


    /**
     * 转换字节数组为十六进制字符串
     *
     * @param b 字节数组
     * @return 十六进制字符串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * 将一个字节转化成十六进制形式的字符串
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * 验证输入的密码是否正确
     *
     * @param password    加密后的密码
     * @param inputString 输入的字符串
     * @return 验证结果，TRUE:正确  FALSE:错误
     * @throws NoSuchAlgorithmException
     */
    public static boolean validatePassword(String password, String inputString) throws NoSuchAlgorithmException {
        if (password.equals(encoderByMd5(inputString))) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(encoderByMd5("123"));
        System.out.println(validatePassword("E10ADC3949BA59ABBE56E057F20F883E", "123456"));
    }
}
