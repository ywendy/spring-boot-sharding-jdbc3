package com.abin.lee.sharding.jdbc.service;

import com.abin.lee.sharding.jdbc.entity.Business;

import java.util.List;

/**
 * Created by lee on 2019/6/16.
 */
public interface BusinessService {

    void insert(Integer userId) throws Exception;

    Business findById(Long id);

    List<Business> findByUserId(Integer userId);

}
