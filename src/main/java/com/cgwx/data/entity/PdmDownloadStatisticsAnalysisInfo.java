package com.cgwx.data.entity;

import java.util.Date;

public class PdmDownloadStatisticsAnalysisInfo {
    private String productName;

    private String productId;

    private Integer productType;

    private String downloadStatus;

    private Date downloadTime;

    private String size;

    private String downloadUser;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(String downloadStatus) {
        this.downloadStatus = downloadStatus == null ? null : downloadStatus.trim();
    }

    public Date getDownloadTime() {
        return downloadTime;
    }

    public void setDownloadTime(Date downloadTime) {
        this.downloadTime = downloadTime;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    public String getDownloadUser() {
        return downloadUser;
    }

    public void setDownloadUser(String downloadUser) {
        this.downloadUser = downloadUser == null ? null : downloadUser.trim();
    }
}