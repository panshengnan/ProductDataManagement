package com.cgwx.data.dto;

import java.sql.Timestamp;

public class AdvanceProductCri {
    private String producer;
    private Object image_geo;
    private String deliverName;
    private String produceArea;
    private String deliverMethod;
    private Timestamp produceStartTime;
    private Timestamp produceEndTime;
    private Timestamp deliverStartTime;
    private Timestamp deliverEndTime;
    private String productName;
    private int curPageNum;
    private int maxResultNum;

    public int getMaxResultNum() {
        return maxResultNum;
    }

    public void setMaxResultNum(int maxResultNum) {
        this.maxResultNum = maxResultNum;
    }

    public int getCurPageNum() {
        return curPageNum;
    }

    public void setCurPageNum(int curPageNum) {
        this.curPageNum = curPageNum;
    }

    boolean ortho;
    boolean inlay;
    boolean subdivision;

    public boolean isOrtho() {
        return ortho;
    }

    public void setOrtho(boolean ortho) {
        this.ortho = ortho;
    }

    public boolean isInlay() {
        return inlay;
    }

    public void setInlay(boolean inlay) {
        this.inlay = inlay;
    }

    public boolean isSubdivision() {
        return subdivision;
    }

    public void setSubdivision(boolean subdivision) {
        this.subdivision = subdivision;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Object getImage_geo() {
        return image_geo;
    }

    public void setImage_geo(Object image_geo) {
        this.image_geo = image_geo;
    }

    public String getDeliverName() {
        return deliverName;
    }

    public void setDeliverName(String deliverName) {
        this.deliverName = deliverName;
    }

    public String getProduceArea() {
        return produceArea;
    }

    public void setProduceArea(String produceArea) {
        this.produceArea = produceArea;
    }

    public String getDeliverMethod() {
        return deliverMethod;
    }

    public void setDeliverMethod(String deliverMethod) {
        this.deliverMethod = deliverMethod;
    }

    public Timestamp getProduceStartTime() {
        return produceStartTime;
    }

    public void setProduceStartTime(Timestamp produceStartTime) {
        this.produceStartTime = produceStartTime;
    }

    public Timestamp getProduceEndTime() {
        return produceEndTime;
    }

    public void setProduceEndTime(Timestamp produceEndTime) {
        this.produceEndTime = produceEndTime;
    }

    public Timestamp getDeliverStartTime() {
        return deliverStartTime;
    }

    public void setDeliverStartTime(Timestamp deliverStartTime) {
        this.deliverStartTime = deliverStartTime;
    }

    public Timestamp getDeliverEndTime() {
        return deliverEndTime;
    }

    public void setDeliverEndTime(Timestamp deliverEndTime) {
        this.deliverEndTime = deliverEndTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
