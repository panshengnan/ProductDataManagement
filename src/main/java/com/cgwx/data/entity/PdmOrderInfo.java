package com.cgwx.data.entity;

import java.math.BigDecimal;
import java.util.Date;

public class PdmOrderInfo {
    private String orderId;

    private Integer orderStatus;

    private BigDecimal orderPrice;

    private BigDecimal orderDiscountPrice;

    private String orderSubmitter;

    private Date submitTime;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getOrderDiscountPrice() {
        return orderDiscountPrice;
    }

    public void setOrderDiscountPrice(BigDecimal orderDiscountPrice) {
        this.orderDiscountPrice = orderDiscountPrice;
    }

    public String getOrderSubmitter() {
        return orderSubmitter;
    }

    public void setOrderSubmitter(String orderSubmitter) {
        this.orderSubmitter = orderSubmitter == null ? null : orderSubmitter.trim();
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }
}