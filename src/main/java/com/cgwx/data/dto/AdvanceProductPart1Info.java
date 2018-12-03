package com.cgwx.data.dto;

import java.util.Date;

public class AdvanceProductPart1Info {
    private String procucerArea;
    private String deliverMethod;
    private Date deliverTime;
    private String deliverName;
    private Date produceTime;
    public String getProcucerArea() {
        return procucerArea;
    }

    public void setProcucerArea(String procucerArea) {
        this.procucerArea = procucerArea;
    }

    public String getDeliverMethod() {
        return deliverMethod;
    }

    public void setDeliverMethod(String deliverMethod) {
        this.deliverMethod = deliverMethod;
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    public String getDeliverName() {
        return deliverName;
    }

    public void setDeliverName(String deliverName) {
        this.deliverName = deliverName;
    }

    public Date getProduceTime() {
        return produceTime;
    }

    public void setProduceTime(Date produceTime) {
        this.produceTime = produceTime;
    }


}
