package com.abin.lee.sharding.jdbc.service;

/**
 * Created by lee on 2019/6/16.
 */
public interface OrderService {

    void insert(Long userId);

    String findById(Long id);


}
