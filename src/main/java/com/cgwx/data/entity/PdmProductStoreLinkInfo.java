package com.cgwx.data.entity;

public class PdmProductStoreLinkInfo {
    private String productId;

    private String singlePeriodProductId;

    private String fileName;

    private String storeLink;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getSinglePeriodProductId() {
        return singlePeriodProductId;
    }

    public void setSinglePeriodProductId(String singlePeriodProductId) {
        this.singlePeriodProductId = singlePeriodProductId == null ? null : singlePeriodProductId.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getStoreLink() {
        return storeLink;
    }

    public void setStoreLink(String storeLink) {
        this.storeLink = storeLink == null ? null : storeLink.trim();
    }
}