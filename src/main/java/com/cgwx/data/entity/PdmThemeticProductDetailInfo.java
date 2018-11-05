package com.cgwx.data.entity;

import java.util.Date;

public class PdmThemeticProductDetailInfo {
    private String productId;

    private String singlePeriodProductId;

    private String singlePeriodProductDirectory;

    private Object imageGeo;

    private String producer;

    private String satellite;

    private String sensor;

    private String sizeOfTif;

    private String singlePeriodProductName;

    private Date produceTime;

    private Date imagingTime;

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

    public String getSinglePeriodProductDirectory() {
        return singlePeriodProductDirectory;
    }

    public void setSinglePeriodProductDirectory(String singlePeriodProductDirectory) {
        this.singlePeriodProductDirectory = singlePeriodProductDirectory == null ? null : singlePeriodProductDirectory.trim();
    }

    public Object getImageGeo() {
        return imageGeo;
    }

    public void setImageGeo(Object imageGeo) {
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

    public String getSinglePeriodProductName() {
        return singlePeriodProductName;
    }

    public void setSinglePeriodProductName(String singlePeriodProductName) {
        this.singlePeriodProductName = singlePeriodProductName == null ? null : singlePeriodProductName.trim();
    }

    public Date getProduceTime() {
        return produceTime;
    }

    public void setProduceTime(Date produceTime) {
        this.produceTime = produceTime;
    }

    public Date getImagingTime() {
        return imagingTime;
    }

    public void setImagingTime(Date imagingTime) {
        this.imagingTime = imagingTime;
    }
}