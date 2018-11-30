package com.cgwx.data.entity;

import java.util.Date;

public class PdmThemeticProductInfo {
    private String productId;

    private String themeticProductName;

    private String industry;

    private String parentDirectory;

    private Integer isMultiPeriod;

    private String clientName;

    private String deliverName;

    private Date deliverTime;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getThemeticProductName() {
        return themeticProductName;
    }

    public void setThemeticProductName(String themeticProductName) {
        this.themeticProductName = themeticProductName == null ? null : themeticProductName.trim();
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    public String getParentDirectory() {
        return parentDirectory;
    }

    public void setParentDirectory(String parentDirectory) {
        this.parentDirectory = parentDirectory == null ? null : parentDirectory.trim();
    }

    public Integer getIsMultiPeriod() {
        return isMultiPeriod;
    }

    public void setIsMultiPeriod(Integer isMultiPeriod) {
        this.isMultiPeriod = isMultiPeriod;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName == null ? null : clientName.trim();
    }

    public String getDeliverName() {
        return deliverName;
    }

    public void setDeliverName(String deliverName) {
        this.deliverName = deliverName == null ? null : deliverName.trim();
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }
}