package com.cgwx.data.dto;

import java.util.Date;

public class ArchivalRecordsItems {

    private String productName;
    private Date archiveTime;
    private int archiveType;
    private String archivePersonnel;
    private int archiveResult;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getArchiveTime() {
        return archiveTime;
    }

    public void setArchiveTime(Date archiveTime) {
        this.archiveTime = archiveTime;
    }

    public int getArchiveType() {
        return archiveType;
    }

    public void setArchiveType(int archiveType) {
        this.archiveType = archiveType;
    }

    public String getArchivePersonnel() {
        return archivePersonnel;
    }

    public void setArchivePersonnel(String archivePersonnel) {
        this.archivePersonnel = archivePersonnel;
    }

    public int getArchiveResult() {
        return archiveResult;
    }

    public void setArchiveResult(int archiveResults) {
        this.archiveResult = archiveResults;
    }
}
