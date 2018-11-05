package com.cgwx.data.entity;

public class PdmArchiveCheckStatusInfo {
    private Integer status;

    private String statusDescription;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription == null ? null : statusDescription.trim();
    }
}