package com.abin.lee.sharding.jdbc.controller;

import com.abin.lee.sharding.jdbc.service.OrderItemService;
import com.abin.lee.sharding.jdbc.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by abin on 2018/8/16.
 */
@RestController
@RequestMapping("/order")
@Slf4j
@Api("OrderController--api")
public class OrderController {

    @Autowired
    OrderService orderService ;
    @Autowired
    OrderItemService orderItemService ;

    @ApiOperation(value = "创建订单", notes = "创建订单")
    @ApiImplicitParam(name =  "addOrder", value = "随机参数", paramType = "query", required = true, dataType = "Integer")
    @PostMapping(value = "/addOrder")
    public String addOrder(Long userId) {
        String result = "SUCCESS" ;
        try {
        this.orderService.insert(userId);
        } catch (Exception e) {
            log.error("add--userId=" + userId + "e=" + e);
            return "FAILURE";
        }
        return result;
    }


    @ApiOperation(value = "根据订单id查询订单", notes = "根据订单id查询订单")
    @ApiImplicitParam(name =  "findById", value = "随机参数", paramType = "query", required = true, dataType = "Integer")
    @PostMapping(value = "/findById")
    public Map<String, Object> findById(Long id) {
        Map<String, Object> result = null ;
        try {
            result = this.orderService.findById(id);
        } catch (Exception e) {
            log.error("findById--id=" + id + "e=" + e);
//            return "FAILURE";
        }
        return result;
    }

    @ApiOperation(value = "创建订单详情", notes = "创建订单详情")
    @PostMapping(value = "/addOrderItem")
    public String addOrderItem(Long orderId,Long userId) {
        String result = "SUCCESS" ;
        try {
            this.orderItemService.insert(orderId, userId);
        } catch (Exception e) {
            log.error("add--userId=" + userId + "e=" + e);
            return "FAILURE";
        }
        return result;
    }




}