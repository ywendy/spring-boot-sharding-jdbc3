package com.abin.lee.sharding.jdbc.controller;

import com.abin.lee.sharding.jdbc.entity.OrderItem;
import com.abin.lee.sharding.jdbc.service.OrderItemService;
import com.abin.lee.sharding.jdbc.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by abin on 2018/8/16.
 */
@RestController
@RequestMapping("/orderItem")
@Slf4j
@Api("OrderItemController--api")
public class OrderItemController {

    @Autowired
    OrderService orderService ;
    @Autowired
    OrderItemService orderItemService ;

    @ApiOperation(value = "创建订单详情", notes = "创建订单详情")
    @ApiImplicitParam(name =  "addOrderItem", value = "随机参数", paramType = "query", required = true, dataType = "Integer")
    @PostMapping(value = "/addOrderItem")
    public String addOrder(Long orderId,Long userId) {
        String result = "SUCCESS" ;
        try {
        this.orderItemService.insert(orderId,userId);
        } catch (Exception e) {
            log.error("add--userId=" + userId + "e=" + e);
            return "FAILURE";
        }
        return result;
    }


    @ApiOperation(value = "根据订单id查询订单详情", notes = "根据订单id查询订单详情")
    @ApiImplicitParam(name =  "findByOrderId", value = "随机参数", paramType = "query", required = true, dataType = "Integer")
    @PostMapping(value = "/findByOrderId")
    public List<OrderItem> findByOrderId(Long orderId) {
        List<OrderItem> result = null ;
        try {
            result = this.orderItemService.findByOrderId(orderId);
        } catch (Exception e) {
            log.error("findById--orderId=" + orderId + "e=" + e);
//            return "FAILURE";
        }
        return result;
    }

    @ApiOperation(value = "根据用户id查询订单详情", notes = "根据用户id查询订单详情")
    @PostMapping(value = "/findByUserId")
    public List<OrderItem> findByUserId(Long userId) {
        List<OrderItem> result = null ;
        try {
            result = this.orderItemService.findByUserId(userId);
        } catch (Exception e) {
            log.error("add--userId=" + userId + "e=" + e);
//            return "FAILURE";
        }
        return result;
    }




}