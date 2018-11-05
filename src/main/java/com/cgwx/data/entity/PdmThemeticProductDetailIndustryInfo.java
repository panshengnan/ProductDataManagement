package com.cgwx.data.entity;

public class PdmThemeticProductDetailIndustryInfo {
    private String productId;

    private Integer industryLevel1;

    private Integer industryLevel2;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public Integer getIndustryLevel1() {
        return industryLevel1;
    }

    public void setIndustryLevel1(Integer industryLevel1) {
        this.industryLevel1 = industryLevel1;
    }

    public Integer getIndustryLevel2() {
        return industryLevel2;
    }

    public void setIndustryLevel2(Integer industryLevel2) {
        this.industryLevel2 = industryLevel2;
    }
}