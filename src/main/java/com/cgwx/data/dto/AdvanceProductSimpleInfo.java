package com.cgwx.data.dto;

public class AdvanceProductSimpleInfo {

    private String productId;
    private Object imageGeo;
    private String productName;
    private String thumbnailUrl;
    private String thumbUrl;

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    String downloadurl;
    String productType;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public void setDownloadurl(String downloadurl) {
        this.downloadurl = downloadurl;
    }

    public String getDownloadurl() {
        return downloadurl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductId() {
        return productId;
    }

    public Object getImageGeo() {
        return imageGeo;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setImageGeo(Object imageGeo) {
        this.imageGeo = imageGeo;
    }


}

