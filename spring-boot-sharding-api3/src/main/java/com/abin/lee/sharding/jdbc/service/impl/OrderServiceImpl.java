package com.abin.lee.sharding.jdbc.service.impl;

import com.abin.lee.sharding.jdbc.common.generator.SnowflakeIdWorker;
import com.abin.lee.sharding.jdbc.common.util.JsonUtil;
import com.abin.lee.sharding.jdbc.entity.Order;
import com.abin.lee.sharding.jdbc.entity.OrderItem;
import com.abin.lee.sharding.jdbc.mapper.OrderMapper;
import com.abin.lee.sharding.jdbc.service.OrderItemService;
import com.abin.lee.sharding.jdbc.service.OrderService;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by lee on 2019/6/16.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    OrderMapper orderMapper;
    @Resource
    OrderItemService orderItemService;

    @Transactional
    @Override
    public void insert(Long userId) {
        Order order = new Order();
        Long id = SnowflakeIdWorker.getId(userId.longValue());
        order.setId(id);
        order.setUserId(userId);
        order.setStatus("0");
        this.orderMapper.insert(order);
//        if(true){
//            throw new RuntimeException("---------i am an exception 1---------------");
//        }
        this.orderItemService.insert(id, userId);
//        if(true){
//            throw new RuntimeException("---------i am an exception 2---------------");
//        }

    }


    @Override
    public Map<String, Object> findById(Long id) {
        Order order = this.orderMapper.selectByPrimaryKey(id);
        List<OrderItem> orderItemList = this.orderItemService.findByOrderId(id);
        Map<String, Object> resuqest = Maps.newHashMap();
        resuqest.put("order", order);
        resuqest.put("orderItem", orderItemList);
        String result = JsonUtil.toJson(order) +" | " +JsonUtil.toJson(orderItemList);
        return resuqest;
    }
}
