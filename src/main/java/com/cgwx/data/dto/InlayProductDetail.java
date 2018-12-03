package com.cgwx.data.dto;

import java.util.Date;
import java.util.List;

public class InlayProductDetail {
        private String productId;

        private String productName;

        private String imageGeo;

//        private String inlayProductDirectory;

        private String producer;

        private String geographicInfo;

      //  private String sizeOfTif;

        private String clientName;

        private String delieverName;

        private Date delieverTime;

      //  private String analysisReportUrl;

        private String thumbnailUrl;

        private String allFileDownloadUrl;

        private List<FileUrl> fileListAndUrl;

        private String productType;
        private String produceArea;
        private Date produceTime;
        private String deliverMethod;
        private List<shpInfo> shpInfoList;
        private String layerName;

    public String getLayerName() {
        return layerName;
    }

    public void setLayerName(String layerName) {
        this.layerName = layerName;
    }

    public List<shpInfo> getShpInfoList() {
        return shpInfoList;
    }

    public void setShpInfoList(List<shpInfo> shpInfoList) {
        this.shpInfoList = shpInfoList;
    }

    public String getProduceArea() {
        return produceArea;
    }

    public void setProduceArea(String produceArea) {
        this.produceArea = produceArea;
    }

    public Date getProduceTime() {
        return produceTime;
    }

    public void setProduceTime(Date produceTime) {
        this.produceTime = produceTime;
    }

    public String getDeliverMethod() {
        return deliverMethod;
    }

    public void setDeliverMethod(String deliverMethod) {
        this.deliverMethod = deliverMethod;
    }

    public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId == null ? null : productId.trim();
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName == null ? null : productName.trim();
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

        public String getGeographicInfo() {
            return geographicInfo;
        }

        public void setGeographicInfo(String geographicInfo) {
            this.geographicInfo = geographicInfo == null ? null : geographicInfo.trim();
        }

//        public String getSizeOfTif() {
//            return sizeOfTif;
//        }
//
//        public void setSizeOfTif(String sizeOfTif) {
//            this.sizeOfTif = sizeOfTif == null ? null : sizeOfTif.trim();
//        }

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

    public void setDelieverTime(Date delieverTime) {
        this.delieverTime = delieverTime;
    }

    public Date getDelieverTime() {
        return delieverTime;
    }

//    public String getAnalysisReportUrl(){
//        return analysisReportUrl;
//    }
//        public void setAnalysisReportUrl(String analysisReportUrl){
//            this.analysisReportUrl = analysisReportUrl == null ? null : analysisReportUrl.trim();
//
//        }

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
