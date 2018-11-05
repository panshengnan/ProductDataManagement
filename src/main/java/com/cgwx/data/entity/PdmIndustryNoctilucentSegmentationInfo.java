package com.cgwx.data.entity;

public class PdmIndustryNoctilucentSegmentationInfo {
    private Integer noctilucentSegmentationCode;

    private String noctilucentSegmentationDescription;

    public Integer getNoctilucentSegmentationCode() {
        return noctilucentSegmentationCode;
    }

    public void setNoctilucentSegmentationCode(Integer noctilucentSegmentationCode) {
        this.noctilucentSegmentationCode = noctilucentSegmentationCode;
    }

    public String getNoctilucentSegmentationDescription() {
        return noctilucentSegmentationDescription;
    }

    public void setNoctilucentSegmentationDescription(String noctilucentSegmentationDescription) {
        this.noctilucentSegmentationDescription = noctilucentSegmentationDescription == null ? null : noctilucentSegmentationDescription.trim();
    }
}