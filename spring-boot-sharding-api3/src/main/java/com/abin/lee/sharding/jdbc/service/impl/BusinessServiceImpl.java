package com.abin.lee.sharding.jdbc.service.impl;

import com.abin.lee.sharding.jdbc.common.generator.GeneratorId;
import com.abin.lee.sharding.jdbc.entity.Business;
import com.abin.lee.sharding.jdbc.mapper.BusinessMapper;
import com.abin.lee.sharding.jdbc.service.BusinessService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lee on 2019/6/16.
 */
@Service
public class BusinessServiceImpl implements BusinessService {

    @Resource
    BusinessMapper businessMapper ;
    @Resource
    GeneratorId generatorId;

    @Override
    public void insert(Integer userId) throws Exception {
        Business business = new Business();
        Long id = this.generatorId.getId(userId.longValue());

        business.setBusinessId(id);
        business.setUserId(userId);
        business.setStatus("0");
        this.businessMapper.insert(business);
    }

    @Override
    public Business findById(Long id) {

        Business business = this.businessMapper.selectByPrimaryKey(id);
        return business ;
    }


    @Override
    public List<Business> findByUserId(Integer userId) {

        List<Business>  businessList = this.businessMapper.findByUserId(userId);

        return businessList;
    }

}
