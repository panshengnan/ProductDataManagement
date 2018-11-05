package com.cgwx.data.entity;

public class PdmIndustryCitySegmentationInfo {
    private Integer citySegmentationCode;

    private String citySegmentationDescription;

    public Integer getCitySegmentationCode() {
        return citySegmentationCode;
    }

    public void setCitySegmentationCode(Integer citySegmentationCode) {
        this.citySegmentationCode = citySegmentationCode;
    }

    public String getCitySegmentationDescription() {
        return citySegmentationDescription;
    }

    public void setCitySegmentationDescription(String citySegmentationDescription) {
        this.citySegmentationDescription = citySegmentationDescription == null ? null : citySegmentationDescription.trim();
    }
}