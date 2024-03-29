package com.abin.lee.sharding.jdbc.entity;

public class Order {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.user_id
     *
     * @mbg.generated
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order.status
     *
     * @mbg.generated
     */
    private String status;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated
     */
    public Order(Long id, Long userId, String status) {
        this.id = id;
        this.userId = userId;
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated
     */
    public Order() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.id
     *
     * @return the value of t_order.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.id
     *
     * @param id the value for t_order.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.user_id
     *
     * @return the value of t_order.user_id
     *
     * @mbg.generated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.user_id
     *
     * @param userId the value for t_order.user_id
     *
     * @mbg.generated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order.status
     *
     * @return the value of t_order.status
     *
     * @mbg.generated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order.status
     *
     * @param status the value for t_order.status
     *
     * @mbg.generated
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}