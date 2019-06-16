package com.abin.lee.sharding.jdbc.test;

import com.abin.lee.sharding.jdbc.ShardingJdbcApplication;
import com.abin.lee.sharding.jdbc.common.generator.SnowflakeIdWorker;
import com.abin.lee.sharding.jdbc.entity.OrderItem;
import com.abin.lee.sharding.jdbc.mapper.OrderItemMapper;
import com.abin.lee.sharding.jdbc.mapper.OrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ShardingJdbcApplication.class)
public class ItemShardingTest {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;


    @Test
//    @Transactional
    public void testItemSharding1() {
        long userId = 29965307745927172L;
        long orderId = SnowflakeIdWorker.getId(4L);
        OrderItem orderItem = new OrderItem();
        long itemId = SnowflakeIdWorker.getId(4L);
        orderItem.setId(itemId);
        orderItem.setOrderId(orderId);
        orderItem.setUserId(userId);
        orderItemMapper.insert(orderItem);
    }


    @Test
    public void testItemSharding2() {
//        long userId = 29965307745927172L;
//        OrderItemExample example = new OrderItemExample();
//        example.createCriteria().andUserIdEqualTo(userId);
//        List<OrderItem> results = orderItemMapper.selectByExample(example);
//        System.out.println("-----------------------------------------------------");
//        System.out.println("results--------= " + JsonUtil.toJson(results));
//        System.out.println("-----------------------------------------------------");
    }

    @Test
    public void testItemSharding3() {
//        long userId = 29965307745927172L;
//        long orderId = 29974974284169220L;
//        OrderItemExample example = new OrderItemExample();
//        example.createCriteria().andUserIdEqualTo(userId).andOrderIdEqualTo(orderId);
//        List<OrderItem> results = orderItemMapper.selectByExample(example);
//        System.out.println("-----------------------------------------------------");
//        System.out.println("results--------= " + JsonUtil.toJson(results));
//        System.out.println("-----------------------------------------------------");
    }

    @Test
    public void testItemSharding4() {
//        long orderId = 29974957947092996L;
//        OrderItemExample example = new OrderItemExample();
//        example.createCriteria().andOrderIdEqualTo(orderId);
//        List<OrderItem> results = orderItemMapper.selectByExample(example);
//        System.out.println("-----------------------------------------------------");
//        System.out.println("results--------= " + JsonUtil.toJson(results));
//        System.out.println("-----------------------------------------------------");
    }

    @Test
    public void testGenetorId() {
        long sequenceMask = -1L ^ (-1L << 8L);
        System.out.println("-----------------------------------------------------");
        System.out.println("-----------------------sequenceMask = " + sequenceMask);
        System.out.println("-----------------------------------------------------");

    }


}
