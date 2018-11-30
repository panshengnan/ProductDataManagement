package com.cgwx.data.dto;

import java.util.List;

public class SinglePeriodThemeticProductDetail {


    private String singlePeriodProductId;


    private String imageGeo;

    private String producer;

    private String satellite;

    private String sensor;

    private String sizeOfTif;

    private String centerImagingTime;

    private String produceTime;

    private String thumbnailUrl;

    private List<FileUrl> fileListAndUrl;

    private String singlePeriodProductName;

    private String layerName;
    public String getSinglePeriodProductId() {
        return singlePeriodProductId;
    }

    public void setSinglePeriodProductId(String singlePeriodProductId) {
        this.singlePeriodProductId = singlePeriodProductId == null ? null : singlePeriodProductId.trim();
    }

    public String getCenterImagingTime() {
        return centerImagingTime;
    }

    public String getLayerName() {
        return layerName;
    }

    public void setLayerName(String layerName) {
        this.layerName = layerName;
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

    public  String getThumbnailUrl(){return  thumbnailUrl;}
    public void setThumbnailUrl(String thumbnailUrl){
        this.thumbnailUrl =thumbnailUrl == null ? null : thumbnailUrl.trim();
    }

    public List<FileUrl> getFileListAndUrl(){return  fileListAndUrl;}
    public void setFileListAndUrl(List<FileUrl> fileListAndUrl){
        this.fileListAndUrl =fileListAndUrl;
    }

    public String getCenterimagingTime() {
        return centerImagingTime;
    }

    public void setCenterImagingTime(String imagingTime) {
        this.centerImagingTime = imagingTime == null ? null : imagingTime.trim();
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
