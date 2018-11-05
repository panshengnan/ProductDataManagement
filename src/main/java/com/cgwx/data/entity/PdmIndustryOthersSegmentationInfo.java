package com.cgwx.data.entity;

public class PdmIndustryOthersSegmentationInfo {
    private Integer othersSegmentationCode;

    private String othersSegmentationDescription;

    public Integer getOthersSegmentationCode() {
        return othersSegmentationCode;
    }

    public void setOthersSegmentationCode(Integer othersSegmentationCode) {
        this.othersSegmentationCode = othersSegmentationCode;
    }

    public String getOthersSegmentationDescription() {
        return othersSegmentationDescription;
    }

    public void setOthersSegmentationDescription(String othersSegmentationDescription) {
        this.othersSegmentationDescription = othersSegmentationDescription == null ? null : othersSegmentationDescription.trim();
    }
}