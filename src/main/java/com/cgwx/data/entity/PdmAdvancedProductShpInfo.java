package com.cgwx.data.entity;

public class PdmAdvancedProductShpInfo {
    private String productId;

    private String imageSourc;

    private String imageFile;

    private String acquisitio;

    private String sensor;

    private String numChannel;

    private String chanType;

    private String cloudCover;

    private String islands;

    private Object imageGeometry;

    private Integer blockId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getImageSourc() {
        return imageSourc;
    }

    public void setImageSourc(String imageSourc) {
        this.imageSourc = imageSourc == null ? null : imageSourc.trim();
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile == null ? null : imageFile.trim();
    }

    public String getAcquisitio() {
        return acquisitio;
    }

    public void setAcquisitio(String acquisitio) {
        this.acquisitio = acquisitio == null ? null : acquisitio.trim();
    }

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor == null ? null : sensor.trim();
    }

    public String getNumChannel() {
        return numChannel;
    }

    public void setNumChannel(String numChannel) {
        this.numChannel = numChannel == null ? null : numChannel.trim();
    }

    public String getChanType() {
        return chanType;
    }

    public void setChanType(String chanType) {
        this.chanType = chanType == null ? null : chanType.trim();
    }

    public String getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(String cloudCover) {
        this.cloudCover = cloudCover == null ? null : cloudCover.trim();
    }

    public String getIslands() {
        return islands;
    }

    public void setIslands(String islands) {
        this.islands = islands == null ? null : islands.trim();
    }

    public Object getImageGeometry() {
        return imageGeometry;
    }

    public void setImageGeometry(Object imageGeometry) {
        this.imageGeometry = imageGeometry;
    }

    public Integer getBlockId() {
        return blockId;
    }

    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }
}