package com.cgwx.data.entity;

import java.math.BigDecimal;
import java.util.Date;

public class PdmCartInfo {
    private String productId;

    private String submitter;

    private Date submitTime;

    private Integer numberOfProducts;

    private BigDecimal productPrice;

    private BigDecimal productDiscountPrice;

    private BigDecimal productDiscount;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter == null ? null : submitter.trim();
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Integer getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(Integer numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
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
}