package com.cgwx.data.entity;

public class PdmIndustryNaturalCalamitiesSegmentationInfo {
    private Integer naturalCalamitiesSegmentationCode;

    private String naturalCalamitiesSegmentationDescription;

    public Integer getNaturalCalamitiesSegmentationCode() {
        return naturalCalamitiesSegmentationCode;
    }

    public void setNaturalCalamitiesSegmentationCode(Integer naturalCalamitiesSegmentationCode) {
        this.naturalCalamitiesSegmentationCode = naturalCalamitiesSegmentationCode;
    }

    public String getNaturalCalamitiesSegmentationDescription() {
        return naturalCalamitiesSegmentationDescription;
    }

    public void setNaturalCalamitiesSegmentationDescription(String naturalCalamitiesSegmentationDescription) {
        this.naturalCalamitiesSegmentationDescription = naturalCalamitiesSegmentationDescription == null ? null : naturalCalamitiesSegmentationDescription.trim();
    }
}