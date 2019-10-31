package com.cos.common.tools;


import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/9/24 13:35
 * @Classname: RandomCharsUtils
 * @To change this template use File | Settings | File Templates.
 */
public class RandomCharsUtils {

    private static String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";

    private static Integer timeMinLength = 14;

    public static synchronized String getRandomChar(int length) {        //length表示生成字符串的长度
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        if (length >= timeMinLength) {
            sb.append(DateUtils.sdfDateMilli.get().format(new Date()));
            for (int i = 0; i < length - timeMinLength; i++) {
                sb.append(base.charAt(random.nextInt(base.length())));
            }
        } else {
            for (int i = 0; i < length; i++) {
                sb.append(base.charAt(random.nextInt(base.length())));
            }
        }
        return sb.toString();
    }
    /*
     * 订单开始交易的时间
     */
    public static String timeStart(){
        return DateUtils.sdfDateMilli.get().format(new Date());
    }

    /*
     * 订单逾期时间
     */
    public static String timeExpire(int activeTime){
        Calendar now=Calendar.getInstance();
        now.add(Calendar.MINUTE, activeTime);
        return DateUtils.sdfDateMilli.get().format(now.getTimeInMillis());
    }
    public static void main(String[] args) {
        System.out.println(getRandomChar(16));
    }
}
