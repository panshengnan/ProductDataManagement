package com.cgwx.data.entity;

public class PdmIndustryTrafficSegmentationInfo {
    private Integer trafficSegmentationCode;

    private String trafficSegmentationDescription;

    public Integer getTrafficSegmentationCode() {
        return trafficSegmentationCode;
    }

    public void setTrafficSegmentationCode(Integer trafficSegmentationCode) {
        this.trafficSegmentationCode = trafficSegmentationCode;
    }

    public String getTrafficSegmentationDescription() {
        return trafficSegmentationDescription;
    }

    public void setTrafficSegmentationDescription(String trafficSegmentationDescription) {
        this.trafficSegmentationDescription = trafficSegmentationDescription == null ? null : trafficSegmentationDescription.trim();
    }
}