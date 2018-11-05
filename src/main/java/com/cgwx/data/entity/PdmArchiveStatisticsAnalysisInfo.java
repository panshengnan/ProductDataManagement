package com.cgwx.data.entity;

import java.util.Date;

public class PdmArchiveStatisticsAnalysisInfo {
    private String productName;

    private String productId;

    private Integer productType;

    private String archiveStatus;

    private Date archiveTime;

    private String size;

    private String archiveUser;

    private String archiveTaskDescription;

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

    public String getArchiveStatus() {
        return archiveStatus;
    }

    public void setArchiveStatus(String archiveStatus) {
        this.archiveStatus = archiveStatus == null ? null : archiveStatus.trim();
    }

    public Date getArchiveTime() {
        return archiveTime;
    }

    public void setArchiveTime(Date archiveTime) {
        this.archiveTime = archiveTime;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    public String getArchiveUser() {
        return archiveUser;
    }

    public void setArchiveUser(String archiveUser) {
        this.archiveUser = archiveUser == null ? null : archiveUser.trim();
    }

    public String getArchiveTaskDescription() {
        return archiveTaskDescription;
    }

    public void setArchiveTaskDescription(String archiveTaskDescription) {
        this.archiveTaskDescription = archiveTaskDescription == null ? null : archiveTaskDescription.trim();
    }
}