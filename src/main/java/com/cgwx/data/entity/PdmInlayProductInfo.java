package com.cgwx.data.entity;

public class PdmInlayProductInfo {
    private String productId;

    private String inlayProductName;

    private Object imageGeo;

    private String inlayProductDirectory;

    private String producer;

    private String geographicInfo;

    private String sizeOfTif;

    private String clientName;

    private String delieverName;

    private String delieverTime;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getInlayProductName() {
        return inlayProductName;
    }

    public void setInlayProductName(String inlayProductName) {
        this.inlayProductName = inlayProductName == null ? null : inlayProductName.trim();
    }

    public Object getImageGeo() {
        return imageGeo;
    }

    public void setImageGeo(Object imageGeo) {
        this.imageGeo = imageGeo;
    }

    public String getInlayProductDirectory() {
        return inlayProductDirectory;
    }

    public void setInlayProductDirectory(String inlayProductDirectory) {
        this.inlayProductDirectory = inlayProductDirectory == null ? null : inlayProductDirectory.trim();
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
}