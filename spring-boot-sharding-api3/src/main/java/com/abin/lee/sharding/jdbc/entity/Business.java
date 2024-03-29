package com.abin.lee.sharding.jdbc.entity;

public class Business {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_business.business_id
     *
     * @mbg.generated
     */
    private Long businessId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_business.user_id
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_business.status
     *
     * @mbg.generated
     */
    private String status;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_business
     *
     * @mbg.generated
     */
    public Business(Long businessId, Integer userId, String status) {
        this.businessId = businessId;
        this.userId = userId;
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_business
     *
     * @mbg.generated
     */
    public Business() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_business.business_id
     *
     * @return the value of t_business.business_id
     *
     * @mbg.generated
     */
    public Long getBusinessId() {
        return businessId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_business.business_id
     *
     * @param businessId the value for t_business.business_id
     *
     * @mbg.generated
     */
    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_business.user_id
     *
     * @return the value of t_business.user_id
     *
     * @mbg.generated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_business.user_id
     *
     * @param userId the value for t_business.user_id
     *
     * @mbg.generated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_business.status
     *
     * @return the value of t_business.status
     *
     * @mbg.generated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_business.status
     *
     * @param status the value for t_business.status
     *
     * @mbg.generated
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}