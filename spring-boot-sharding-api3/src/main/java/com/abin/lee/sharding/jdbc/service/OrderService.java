package com.abin.lee.sharding.jdbc.service;

import java.util.Map;

/**
 * Created by lee on 2019/6/16.
 */
public interface OrderService {

    void insert(Long userId);

    Map<String, Object> findById(Long id);


}
