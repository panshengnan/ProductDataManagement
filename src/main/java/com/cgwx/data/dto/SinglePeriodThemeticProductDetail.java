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



    //private String analysisReportUrl;
    //private String allFileDownloadUrl;

    private String thumbnailUrl;



    private List<FileUrl> fileListAndUrl;

    private String singlePeriodProductName;


//    private String resolution;
//
//    private String imageBreath;
//
//    private String cloudPercent;
//
//    private String centerImagingTime;
//    private String singlePeriodProductDirectory;
//    private String nation;
//
//    private String province;
//
//    private String city;
//
//    private String county;
//
//    private String village;
//
//    private String geographicInfo;



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


//    public  String getAnalysisReportUrl(){return  analysisReportUrl;}
//    public void setAnalysisReportUrl(String analysisReportUrl){
//        this.analysisReportUrl =analysisReportUrl == null ? null : analysisReportUrl.trim();
//    }
    public  String getThumbnailUrl(){return  thumbnailUrl;}
    public void setThumbnailUrl(String thumbnailUrl){
        this.thumbnailUrl =thumbnailUrl == null ? null : thumbnailUrl.trim();
    }

    public List<FileUrl> getFileListAndUrl(){return  fileListAndUrl;}
    public void setFileListAndUrl(List<FileUrl> fileListAndUrl){
        this.fileListAndUrl =fileListAndUrl;
    }

//    public String getAllFileDownloadUrl() {
//        return allFileDownloadUrl;
//    }
//    public void setAllFileDownloadUrl(String allFileDownloadUrl){
//        this.allFileDownloadUrl=allFileDownloadUrl;
//    }



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

//    public String getNation() {
//        return nation;
//    }
//
//    public void setNation(String nation) {
//        this.nation = nation == null ? null : nation.trim();
//    }
//
//    public String getProvince() {
//        return province;
//    }
//
//    public void setProvince(String province) {
//        this.province = province == null ? null : province.trim();
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city == null ? null : city.trim();
//    }
//
//    public String getCounty() {
//        return county;
//    }
//
//    public void setCounty(String county) {
//        this.county = county == null ? null : county.trim();
//    }
//
//    public String getVillage() {
//        return village;
//    }
//
//    public void setVillage(String village) {
//        this.village = village == null ? null : village.trim();
//    }
//
//    public String getGeographicInfo() {
//        return geographicInfo;
//    }
//
//    public void setGeographicInfo(String geographicInfo) {
//        this.geographicInfo = geographicInfo == null ? null : geographicInfo.trim();
//    }


    //    public String getResolution() {
//        return resolution;
//    }
//
//    public void setResolution(String resolution) {
//        this.resolution = resolution;
//    }
//
//    public String getImageBreath() {
//        return imageBreath;
//    }
//
//    public void setImageBreath(String imageBreath) {
//        this.imageBreath = imageBreath == null ? null : imageBreath.trim();
//    }
//
//    public String getCloudPercent() {
//        return cloudPercent;
//    }
//
//    public void setCloudPercent(String cloudPercent) {
//        this.cloudPercent = cloudPercent;
//    }
//
//    public String getCenterImagingTime() {
//        return centerImagingTime;
//    }
//
//    public void setCenterImagingTime(String centerImagingTime) {
//        this.centerImagingTime = centerImagingTime;
//    }





}
