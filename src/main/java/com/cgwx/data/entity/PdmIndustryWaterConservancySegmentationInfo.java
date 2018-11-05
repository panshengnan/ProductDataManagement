package com.cgwx.data.entity;

public class PdmIndustryWaterConservancySegmentationInfo {
    private Integer waterConservancySegmentationCode;

    private String waterConservancySegmentationDescription;

    public Integer getWaterConservancySegmentationCode() {
        return waterConservancySegmentationCode;
    }

    public void setWaterConservancySegmentationCode(Integer waterConservancySegmentationCode) {
        this.waterConservancySegmentationCode = waterConservancySegmentationCode;
    }

    public String getWaterConservancySegmentationDescription() {
        return waterConservancySegmentationDescription;
    }

    public void setWaterConservancySegmentationDescription(String waterConservancySegmentationDescription) {
        this.waterConservancySegmentationDescription = waterConservancySegmentationDescription == null ? null : waterConservancySegmentationDescription.trim();
    }
}