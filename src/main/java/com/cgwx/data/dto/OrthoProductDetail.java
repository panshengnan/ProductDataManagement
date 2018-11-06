package com.cgwx.data.dto;

import java.util.List;

public class OrthoProductDetail {

        private String productId;

        private String orthoProductName;

        private String imageGeo;

 //       private String orthoProductDirectory;

        private String producer;

        private String geographicInfo;

        private String satellite;

        private String sensor;

        private String resolution;

        private String imageBreath;

        private String captureTime;

        private String sizeOfTif;

        private String clientName;

        private String delieverName;

        private String delieverTime;

        private String analysisReportUrl;

        private String thumbnailUrl;

        private String allFileDownloadUrl;

        private List<FileUrl> fileListAndUrl;

        private String productType;

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId == null ? null : productId.trim();
        }

        public String getOrthoProductName() {
            return orthoProductName;
        }

        public void setOrthoProductName(String orthoProductName) {
            this.orthoProductName = orthoProductName == null ? null : orthoProductName.trim();
        }

        public String getImageGeo() {
            return imageGeo;
        }

        public void setImageGeo(String imageGeo) {
            this.imageGeo = imageGeo;
        }

//        public String getOrthoProductDirectory() {
//            return orthoProductDirectory;
//        }
//
//        public void setOrthoProductDirectory(String orthoProductDirectory) {
//            this.orthoProductDirectory = orthoProductDirectory == null ? null : orthoProductDirectory.trim();
//        }

        public String getProducer() {
            return producer;
        }

        public void setProducer(String producer) {
            this.producer = producer == null ? null : producer.trim();
        }

        public String getGeographicInfo() {
            return geographicInfo;
        }

        public void setGeographicInfo(String geographicInfo) {
            this.geographicInfo = geographicInfo == null ? null : geographicInfo.trim();
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

        public String getResolution() {
            return resolution;
        }

        public void setResolution(String  resolution) {
            this.resolution = resolution;
        }

        public String getImageBreath() {
            return imageBreath;
        }

        public void setImageBreath(String imageBreath) {
            this.imageBreath = imageBreath == null ? null : imageBreath.trim();
        }

        public String getCaptureTime() {
            return captureTime;
        }

        public void setCaptureTime(String captureTime) {
            this.captureTime = captureTime;
        }

        public String getSizeOfTif() {
            return sizeOfTif;
        }

        public void setSizeOfTif(String sizeOfTif) {
            this.sizeOfTif = sizeOfTif == null ? null : sizeOfTif.trim();
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

        public String getAnalysisReportUrl(){
            return analysisReportUrl;
        }
        public void setAnalysisReportUrl(String analysisReportUrl){
            this.analysisReportUrl = analysisReportUrl == null ? null : analysisReportUrl.trim();

        }

        public String getThumbnailUrl(){
            return thumbnailUrl;
        }

        public void setThumbnailUrl(String thumbnailUrl){
            this.thumbnailUrl= thumbnailUrl == null ? null : thumbnailUrl.trim();

        }
        public String getAllFileDownloadUrl(){
            return allFileDownloadUrl;
        }

        public void setAllFileDownloadUrl(String allFileDownloadUrl){
            this.allFileDownloadUrl= allFileDownloadUrl == null ? null : allFileDownloadUrl.trim();

        }

        public List<FileUrl> getFileListAndUrl(){
            return fileListAndUrl;
        }
        public  void  setFileListAndUrl(List<FileUrl> fileListAndUrl){
            this.fileListAndUrl =fileListAndUrl;
        }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }



    }
