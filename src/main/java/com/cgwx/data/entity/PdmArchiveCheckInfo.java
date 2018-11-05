package com.cgwx.data.entity;

public class PdmArchiveCheckInfo {
    private String productId;

    private Integer status;

    private String temporaryPath;

    private String fileName;

    private Integer isMultiPeriod;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTemporaryPath() {
        return temporaryPath;
    }

    public void setTemporaryPath(String temporaryPath) {
        this.temporaryPath = temporaryPath == null ? null : temporaryPath.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public Integer getIsMultiPeriod() {
        return isMultiPeriod;
    }

    public void setIsMultiPeriod(Integer isMultiPeriod) {
        this.isMultiPeriod = isMultiPeriod;
    }
}