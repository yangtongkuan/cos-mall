package com.cos.common.tools;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 *
 * @desc 订单需要生成方式  每毫秒可以产生100个订单号 支持集群部署  最多支持9个机器
 * @User: @Created by yangtk
 * @Date: @Date 2019/9/23 11:37
 * @Classname: OrderNOUtils
 * @To change this template use File | Settings | File Templates.
 */
@Slf4j
public class OrderNOUtils {

    /**
     * 支持的最大机器id，目前只支持0-9 可以根据业务需要扩展 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     */
    private final long maxWorkerId = 9;
    /**
     * 工作机器ID(0~31)
     */
    private long workerId;

    /**
     * 毫秒内序列(0~4095)
     */
    private long sequence = 0L;

    /**
     * 最大序列为多少 目前可以为1~99;
     */
    private long maxSequence = 99;

    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;

    private static OrderNOUtils orderWorker;

    enum Singleton {
        INSTANCE;
        private OrderNOUtils single = null;

        private Singleton() {
            InputStream in = OrderNOUtils.class.getClassLoader().getResourceAsStream("application.properties");
            Properties prop = new Properties();
            try {
                prop.load(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                String orderNo_workId = prop.getProperty("orderNo.workId");
                if (orderNo_workId == null) {
                    orderNo_workId = "1";
                }
                final Long wId = Long.parseLong(orderNo_workId);
                single = new OrderNOUtils(wId);
            } catch (Exception e) {
                log.error("dataCenterId or workId is not  configured");
                throw new IllegalArgumentException(String.format("dataCenterId or workId is not  configured"));
            }
        }

        private OrderNOUtils getInstance() {
            return single;
        }
    }

    public static OrderNOUtils getInstance() {
        return Singleton.INSTANCE.getInstance();
    }
    //==============================Constructors=====================================

    /**
     * 构造函数
     *
     * @param workerId 工作ID (0~9)
     */
    public OrderNOUtils(long workerId) {
        if (workerId > maxWorkerId || workerId < 0) {
            log.error("workerId can't be greater than {} or less than 0", maxWorkerId);
            throw new IllegalArgumentException(String.format("workerId can't be greater than %d or less than 0", maxWorkerId));
        }
        this.workerId = workerId;
    }

    // ==============================Methods==========================================

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    public synchronized String nextOrderNo() {
        long timestamp = timeGen();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            log.error("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp);
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            if ((sequence = (sequence + 1)) > maxSequence) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(timestamp);
                sequence = 0L;
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }
        //上次生成orderNo的时间截
        lastTimestamp = timestamp;
        //移位并通过或运算拼到一起组成64位的ID
        return DateUtils.sdfDateMilliSeconds.get().format(new Date(timestamp))
                + String.format("%02d", this.workerId)
                + String.format("%02d", this.sequence);
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }


    /**
     * 测试
     */
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            String orderNo = OrderNOUtils.getInstance().nextOrderNo();
            System.out.println(orderNo);
        }
        System.out.println((System.currentTimeMillis() - startTime) / 1000 + "ms");
    }


}
