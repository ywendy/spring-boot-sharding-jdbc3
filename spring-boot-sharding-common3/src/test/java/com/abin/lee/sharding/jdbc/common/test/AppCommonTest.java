package com.abin.lee.sharding.jdbc.common.test;

/**
 * Created by abin on 2018/6/6.
 */
public class AppCommonTest {
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        System.out.println(time);
        String result1 = "10000000000000000000000000000000";
        System.out.println(result1.length());

        String result2 = Long.toBinaryString(2147483648L);
        System.out.println(result2);
        System.out.println(result2.length());

    }
}
