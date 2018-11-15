package com.cgwx.data.dto;

public class ThemeticProductSimpleInfo {
    String productId;
    Object imageGeo;
    String singlePeriodId;

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setImageGeo(Object imageGeo) {
        this.imageGeo = imageGeo;
    }

    public void setSinglePeriodId(String singlePeriodId) {
        this.singlePeriodId = singlePeriodId;
    }

    public String getProductId() {
        return productId;
    }

    public Object getImageGeo() {
        return imageGeo;
    }

    public String getSinglePeriodId() {
        return singlePeriodId;
    }
}
