package com.cgwx.data.entity;

public class PdmIndustryForestrySegmentationInfo {
    private Integer forestrySegmentationCode;

    private String forestrySegmentationDescription;

    public Integer getForestrySegmentationCode() {
        return forestrySegmentationCode;
    }

    public void setForestrySegmentationCode(Integer forestrySegmentationCode) {
        this.forestrySegmentationCode = forestrySegmentationCode;
    }

    public String getForestrySegmentationDescription() {
        return forestrySegmentationDescription;
    }

    public void setForestrySegmentationDescription(String forestrySegmentationDescription) {
        this.forestrySegmentationDescription = forestrySegmentationDescription == null ? null : forestrySegmentationDescription.trim();
    }
}