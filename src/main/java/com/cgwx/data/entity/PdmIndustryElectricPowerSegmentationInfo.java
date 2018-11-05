package com.cgwx.data.entity;

public class PdmIndustryElectricPowerSegmentationInfo {
    private Integer electricPowerSegmentationCode;

    private String electricPowerSegmentationDescription;

    public Integer getElectricPowerSegmentationCode() {
        return electricPowerSegmentationCode;
    }

    public void setElectricPowerSegmentationCode(Integer electricPowerSegmentationCode) {
        this.electricPowerSegmentationCode = electricPowerSegmentationCode;
    }

    public String getElectricPowerSegmentationDescription() {
        return electricPowerSegmentationDescription;
    }

    public void setElectricPowerSegmentationDescription(String electricPowerSegmentationDescription) {
        this.electricPowerSegmentationDescription = electricPowerSegmentationDescription == null ? null : electricPowerSegmentationDescription.trim();
    }
}