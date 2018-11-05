package com.cgwx.data.entity;

import java.math.BigDecimal;

public class PdmSubdivisionProductInfo {
    private String productId;

    private String subdivisionProductName;

    private Object imageGeo;

    private String subdivisionProductDirectory;

    private Integer numberOfTif;

    private String industry;

    private String producer;

    private String clientName;

    private String delieverName;

    private String delieverTime;

    private BigDecimal resolution;

    private String geographicInfo;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getSubdivisionProductName() {
        return subdivisionProductName;
    }

    public void setSubdivisionProductName(String subdivisionProductName) {
        this.subdivisionProductName = subdivisionProductName == null ? null : subdivisionProductName.trim();
    }

    public Object getImageGeo() {
        return imageGeo;
    }

    public void setImageGeo(Object imageGeo) {
        this.imageGeo = imageGeo;
    }

    public String getSubdivisionProductDirectory() {
        return subdivisionProductDirectory;
    }

    public void setSubdivisionProductDirectory(String subdivisionProductDirectory) {
        this.subdivisionProductDirectory = subdivisionProductDirectory == null ? null : subdivisionProductDirectory.trim();
    }

    public Integer getNumberOfTif() {
        return numberOfTif;
    }

    public void setNumberOfTif(Integer numberOfTif) {
        this.numberOfTif = numberOfTif;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer == null ? null : producer.trim();
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName == null ? null : clientName.trim();
    }

    public String getDelieverName() {
        return delieverName;
    }

    public void setDelieverName(String delieverName) {
        this.delieverName = delieverName == null ? null : delieverName.trim();
    }

    public String getDelieverTime() {
        return delieverTime;
    }

    public void setDelieverTime(String delieverTime) {
        this.delieverTime = delieverTime == null ? null : delieverTime.trim();
    }

    public BigDecimal getResolution() {
        return resolution;
    }

    public void setResolution(BigDecimal resolution) {
        this.resolution = resolution;
    }

    public String getGeographicInfo() {
        return geographicInfo;
    }

    public void setGeographicInfo(String geographicInfo) {
        this.geographicInfo = geographicInfo == null ? null : geographicInfo.trim();
    }
}