package com.cgwx.data.entity;

public class PdmProductLayerInfo {
    private String productId;

    private String singleId;

    private String layerName;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getSingleId() {
        return singleId;
    }

    public void setSingleId(String singleId) {
        this.singleId = singleId == null ? null : singleId.trim();
    }

    public String getLayerName() {
        return layerName;
    }

    public void setLayerName(String layerName) {
        this.layerName = layerName == null ? null : layerName.trim();
    }
}