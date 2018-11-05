package com.cgwx.data.entity;

public class PdmIndustryEnvironmentalProtectionSegmentationInfo {
    private Integer environmentalProtectionSegmentationCode;

    private String environmentalProtectionSegmentationDescription;

    public Integer getEnvironmentalProtectionSegmentationCode() {
        return environmentalProtectionSegmentationCode;
    }

    public void setEnvironmentalProtectionSegmentationCode(Integer environmentalProtectionSegmentationCode) {
        this.environmentalProtectionSegmentationCode = environmentalProtectionSegmentationCode;
    }

    public String getEnvironmentalProtectionSegmentationDescription() {
        return environmentalProtectionSegmentationDescription;
    }

    public void setEnvironmentalProtectionSegmentationDescription(String environmentalProtectionSegmentationDescription) {
        this.environmentalProtectionSegmentationDescription = environmentalProtectionSegmentationDescription == null ? null : environmentalProtectionSegmentationDescription.trim();
    }
}