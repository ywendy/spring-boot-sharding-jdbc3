package com.abin.lee.sharding.jdbc.service;

import com.abin.lee.sharding.jdbc.entity.OrderItem;

import java.util.List;

/**
 * Created by lee on 2019/6/16.
 */
public interface OrderItemService {

    void insert(Long orderId,Long userId);

    List<OrderItem> findByOrderId(Long orderId);

    List<OrderItem> findByUserId(Long userId);



}
