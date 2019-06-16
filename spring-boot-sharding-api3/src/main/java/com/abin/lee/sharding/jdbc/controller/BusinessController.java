package com.abin.lee.sharding.jdbc.controller;

import com.abin.lee.sharding.jdbc.entity.Business;
import com.abin.lee.sharding.jdbc.service.BusinessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by abin on 2018/8/16.
 */
@RestController
@RequestMapping("/business")
@Slf4j
@Api("BusinessController--api")
public class BusinessController {
    @Resource
    BusinessService businessService ;

    @ApiOperation(value = "增加商品", notes = "增加商品")
    @PostMapping(value = "/addBusiness")
    public String addBusiness(Integer userId) {
        String result = "SUCCESS" ;
        try {
            this.businessService.insert(userId);
        } catch (Exception e) {
            log.error("addBusiness--userId=" + userId + "e=" + e);
            return "FAILURE";
        }
        return result;
    }

    @ApiOperation(value = "根据主键查询", notes = "根据主键查询")
    @PostMapping(value = "/findById")
    public Business findById(Long id) {
        String result = "SUCCESS" ;
        Business business = null ;
        try {
            business = this.businessService.findById(id);
        } catch (Exception e) {
            log.error("findById--id=" + id + "e=" + e);
//            return "FAILURE";
        }
        return business;
    }


    @ApiOperation(value = "根据userId查询", notes = "根据userId查询")
    @PostMapping(value = "/findByUserId")
    public List<Business> findByUserId(Integer userId) {
        String result = "SUCCESS" ;
        List<Business> businessList = null;
        try {
            businessList = this.businessService.findByUserId(userId);
        } catch (Exception e) {
            log.error("add--userId=" + userId + "e=" + e);
//            return "FAILURE";
        }
        return businessList;
    }




}