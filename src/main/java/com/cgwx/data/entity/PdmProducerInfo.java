package com.cgwx.data.entity;

public class PdmProducerInfo {
    private String producer;

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer == null ? null : producer.trim();
    }
}