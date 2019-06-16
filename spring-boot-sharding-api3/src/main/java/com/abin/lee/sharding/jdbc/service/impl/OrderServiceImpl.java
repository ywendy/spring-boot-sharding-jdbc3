package com.abin.lee.sharding.jdbc.service.impl;

import com.abin.lee.sharding.jdbc.common.generator.SnowflakeIdWorker;
import com.abin.lee.sharding.jdbc.common.util.JsonUtil;
import com.abin.lee.sharding.jdbc.entity.Order;
import com.abin.lee.sharding.jdbc.entity.OrderItem;
import com.abin.lee.sharding.jdbc.mapper.OrderMapper;
import com.abin.lee.sharding.jdbc.service.OrderItemService;
import com.abin.lee.sharding.jdbc.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lee on 2019/6/16.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    OrderMapper orderMapper;
    @Resource
    OrderItemService orderItemService;

    @Override
    public void insert(Long userId) {
        Order order = new Order();
        Long id = SnowflakeIdWorker.getId(userId.longValue());
        order.setId(id);
        order.setUserId(userId);
        order.setStatus("0");
        this.orderMapper.insert(order);
        this.orderItemService.insert(id, userId);

    }


    @Override
    public String findById(Long id) {
        Order order = this.orderMapper.selectByPrimaryKey(id);
        List<OrderItem> orderItemList = this.orderItemService.findByOrderId(id);
        String result = JsonUtil.toJson(order) +" | " +JsonUtil.toJson(orderItemList);
        return result;
    }
}
