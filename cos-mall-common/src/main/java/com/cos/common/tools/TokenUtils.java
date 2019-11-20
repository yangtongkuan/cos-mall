package com.cos.common.tools;

import org.apache.commons.lang.StringUtils;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/8/5 13:18
 * @Classname: TokenUtils
 * @To 生成token
 */
public class TokenUtils {

    // 随机产生范围
    private final static String[] scopes = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "g", "k", "l", "m", "n"
            , "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};


    public static synchronized String createToken() {
        String token = IdWorker.getInstance().nextId() + "";
        return getNeedStr(token, 32);
    }

    public static String getNeedStr(String val, int length) {
        StringBuilder data = new StringBuilder();
        if (StringUtils.isEmpty(val)) {
            for (int index = 0; index < length; index++) {
                data.append(scopes[((int) (Math.random() * 100)) % scopes.length]);
            }
        } else if (val.length() > length) {
            data.append(val.substring(0, length));
        } else {
            data.append(val);
            while (data.toString().length() < length) {
                data.append(scopes[((int) (Math.random() * 100)) % scopes.length]);
            }
        }
        return data.toString().toUpperCase();
    }

    public static void main(String[] args) {
//        for (int index = 0; index < 500; index++)
        System.out.println(createToken());
    }

}
