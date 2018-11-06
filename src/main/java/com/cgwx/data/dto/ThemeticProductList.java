package com.cgwx.data.dto;

public class ThemeticProductList {

    private String productId;

    private String themeticProductName;

    private String industry;

    private String clientName;

    private String delieverName;

    private String delieverTime;

    private String singlePeriodProductId;

    private String imageGeo;

    private String producer;

    private String satellite;

    private String sensor;
    private String sizeOfTif;

    private String imagingTime;

    private String produceTime;

    private String singlePeriodProductName;

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



    public String getSinglePeriodProductId() {
        return singlePeriodProductId;
    }

    public void setSinglePeriodProductId(String singlePeriodProductId) {
        this.singlePeriodProductId = singlePeriodProductId == null ? null : singlePeriodProductId.trim();
    }


    public String getImageGeo() {
        return imageGeo;
    }

    public void setImageGeo(String imageGeo) {
        this.imageGeo = imageGeo;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer == null ? null : producer.trim();
    }

    public String getSatellite() {
        return satellite;
    }

    public void setSatellite(String satellite) {
        this.satellite = satellite == null ? null : satellite.trim();
    }

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor == null ? null : sensor.trim();
    }


    public String getSizeOfTif() {
        return sizeOfTif;
    }

    public void setSizeOfTif(String sizeOfTif) {
        this.sizeOfTif = sizeOfTif == null ? null : sizeOfTif.trim();
    }



    public String getImagingTime() {
        return imagingTime;
    }

    public void setImagingTime(String imagingTime) {
        this.imagingTime = imagingTime == null ? null : imagingTime.trim();
    }

    public String getProduceTime() {
        return produceTime;
    }

    public void setProduceTime(String produceTime) {
        this.produceTime = produceTime == null ? null : produceTime.trim();
    }


    public String getSinglePeriodProductName(){return singlePeriodProductName;}
    public void setSinglePeriodProductName(String singlePeriodProductName) {
        this.singlePeriodProductName =singlePeriodProductName == null ? null : singlePeriodProductName.trim();

    }


}
