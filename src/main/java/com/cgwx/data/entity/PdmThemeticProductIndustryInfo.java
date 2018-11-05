package com.cgwx.data.entity;

public class PdmThemeticProductIndustryInfo {
    private Integer industryCode;

    private String industryDescription;

    public Integer getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(Integer industryCode) {
        this.industryCode = industryCode;
    }

    public String getIndustryDescription() {
        return industryDescription;
    }

    public void setIndustryDescription(String industryDescription) {
        this.industryDescription = industryDescription == null ? null : industryDescription.trim();
    }
}