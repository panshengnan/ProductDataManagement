package com.cgwx.data.entity;

public class PdmIndustryTerritorySegmentationInfo {
    private Integer territorySegmentationCode;

    private String territorySegmentationDescription;

    public Integer getTerritorySegmentationCode() {
        return territorySegmentationCode;
    }

    public void setTerritorySegmentationCode(Integer territorySegmentationCode) {
        this.territorySegmentationCode = territorySegmentationCode;
    }

    public String getTerritorySegmentationDescription() {
        return territorySegmentationDescription;
    }

    public void setTerritorySegmentationDescription(String territorySegmentationDescription) {
        this.territorySegmentationDescription = territorySegmentationDescription == null ? null : territorySegmentationDescription.trim();
    }
}