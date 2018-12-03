package com.cgwx.data.dto;

import java.util.Date;

public class PdmArchiveRecordsInfoStr {

    private String productId;

    private String productName;

    private String archiveTime;

    private Integer archiveType;

    private String archivePersonnel;

    private Integer archiveResult;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getArchiveTime() {
        return archiveTime;
    }

    public void setArchiveTime(String archiveTime) {
        this.archiveTime = archiveTime;
    }

    public Integer getArchiveType() {
        return archiveType;
    }

    public void setArchiveType(Integer archiveType) {
        this.archiveType = archiveType;
    }

    public String getArchivePersonnel() {
        return archivePersonnel;
    }

    public void setArchivePersonnel(String archivePersonnel) {
        this.archivePersonnel = archivePersonnel;
    }

    public Integer getArchiveResult() {
        return archiveResult;
    }

    public void setArchiveResult(Integer archiveResult) {
        this.archiveResult = archiveResult;
    }
}
