package com.cgwx.data.entity;

public class PdmIndustryHousingConstructionSegmentationInfo {
    private Integer housingConstructionSegmentationCode;

    private String housingConstructionSegmentationDescription;

    public Integer getHousingConstructionSegmentationCode() {
        return housingConstructionSegmentationCode;
    }

    public void setHousingConstructionSegmentationCode(Integer housingConstructionSegmentationCode) {
        this.housingConstructionSegmentationCode = housingConstructionSegmentationCode;
    }

    public String getHousingConstructionSegmentationDescription() {
        return housingConstructionSegmentationDescription;
    }

    public void setHousingConstructionSegmentationDescription(String housingConstructionSegmentationDescription) {
        this.housingConstructionSegmentationDescription = housingConstructionSegmentationDescription == null ? null : housingConstructionSegmentationDescription.trim();
    }
}