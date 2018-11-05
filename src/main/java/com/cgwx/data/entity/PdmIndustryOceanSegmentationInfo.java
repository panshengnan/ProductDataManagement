package com.cgwx.data.entity;

public class PdmIndustryOceanSegmentationInfo {
    private Integer oceanSegmentationCode;

    private String oceanSegmentationDescription;

    public Integer getOceanSegmentationCode() {
        return oceanSegmentationCode;
    }

    public void setOceanSegmentationCode(Integer oceanSegmentationCode) {
        this.oceanSegmentationCode = oceanSegmentationCode;
    }

    public String getOceanSegmentationDescription() {
        return oceanSegmentationDescription;
    }

    public void setOceanSegmentationDescription(String oceanSegmentationDescription) {
        this.oceanSegmentationDescription = oceanSegmentationDescription == null ? null : oceanSegmentationDescription.trim();
    }
}