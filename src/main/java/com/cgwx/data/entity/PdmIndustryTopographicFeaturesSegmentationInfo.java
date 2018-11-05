package com.cgwx.data.entity;

public class PdmIndustryTopographicFeaturesSegmentationInfo {
    private Integer topographicFeaturesSegmentationCode;

    private String topographicFeaturesSegmentationDescription;

    public Integer getTopographicFeaturesSegmentationCode() {
        return topographicFeaturesSegmentationCode;
    }

    public void setTopographicFeaturesSegmentationCode(Integer topographicFeaturesSegmentationCode) {
        this.topographicFeaturesSegmentationCode = topographicFeaturesSegmentationCode;
    }

    public String getTopographicFeaturesSegmentationDescription() {
        return topographicFeaturesSegmentationDescription;
    }

    public void setTopographicFeaturesSegmentationDescription(String topographicFeaturesSegmentationDescription) {
        this.topographicFeaturesSegmentationDescription = topographicFeaturesSegmentationDescription == null ? null : topographicFeaturesSegmentationDescription.trim();
    }
}