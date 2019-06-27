package com.abin.lee.sharding.jdbc.common.test.common;

import com.abin.lee.sharding.jdbc.common.test.entity.OrderItem;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;

import java.util.Objects;

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

    @Test
    public void test1(){
        OrderItem order = new OrderItem();
        OrderItem order1 = null;
        boolean flag = Objects.nonNull(order);
        System.out.println("flag="+flag);
        boolean flag1 = Objects.nonNull(order1);
        System.out.println("flag1="+flag1);
        boolean flag2 = ObjectUtils.notEqual(null, order);
        System.out.println("flag2="+flag2);
    }



}
