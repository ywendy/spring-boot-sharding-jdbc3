package com.abin.lee.sharding.jdbc.mapper;

import com.abin.lee.sharding.jdbc.entity.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderItemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_item
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_item
     *
     * @mbg.generated
     */
    int insert(OrderItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_item
     *
     * @mbg.generated
     */
    int insertSelective(OrderItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_item
     *
     * @mbg.generated
     */
    OrderItem selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_item
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(OrderItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_item
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(OrderItem record);


    List<OrderItem> findByOrderId(@Param("orderId")Long orderId);

    List<OrderItem> findByUserId(@Param("userId") Long userId);
}