package com.abin.lee.sharding.jdbc.service.impl;

import com.abin.lee.sharding.jdbc.common.generator.GeneratorId;
import com.abin.lee.sharding.jdbc.entity.OrderItem;
import com.abin.lee.sharding.jdbc.mapper.OrderItemMapper;
import com.abin.lee.sharding.jdbc.service.OrderItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lee on 2019/6/16.
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {


    @Resource
    OrderItemMapper orderItemMapper ;
    @Resource
    GeneratorId generatorId;

    @Override
    public void insert(Long orderId,Long userId) throws Exception {
        OrderItem orderItem = new OrderItem();
        Long id = this.generatorId.getId(userId);

        orderItem.setId(id);
        orderItem.setUserId(userId);
        orderItem.setOrderId(orderId);
        this.orderItemMapper.insert(orderItem);

    }

    @Override
    public List<OrderItem> findByOrderId(Long orderId) {
        List<OrderItem> orderItemList = this.orderItemMapper.findByOrderId(orderId);
        return orderItemList;
    }


    @Override
    public List<OrderItem> findByUserId(Long userId) {
        List<OrderItem> orderItemList = this.orderItemMapper.findByUserId(userId);
        return orderItemList;
    }
}
