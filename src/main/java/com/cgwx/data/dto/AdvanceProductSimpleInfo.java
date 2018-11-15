package com.cgwx.data.dto;

public class AdvanceProductSimpleInfo {

    String productId;
    Object imageGeo;
    String productName;

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductId() {
        return productId;
    }

    public Object getImageGeo() {
        return imageGeo;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setImageGeo(Object imageGeo) {
        this.imageGeo = imageGeo;
    }


}

