package com.cgwx.data.entity;

import java.math.BigDecimal;

public class PdmOrderProductInfo {
    private String orderId;

    private String orderProduct;

    private BigDecimal productPrice;

    private BigDecimal productDiscountPrice;

    private BigDecimal productDiscount;

    private Integer numberOfProducts;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(String orderProduct) {
        this.orderProduct = orderProduct == null ? null : orderProduct.trim();
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getProductDiscountPrice() {
        return productDiscountPrice;
    }

    public void setProductDiscountPrice(BigDecimal productDiscountPrice) {
        this.productDiscountPrice = productDiscountPrice;
    }

    public BigDecimal getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(BigDecimal productDiscount) {
        this.productDiscount = productDiscount;
    }

    public Integer getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(Integer numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }
}