package com.cgwx.data.dto;

public class AreaInfoDto {
    private Integer areaId;
    private String areaName;
    private Object areaGeojson;

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Object getAreaGeojson() {
        return areaGeojson;
    }

    public void setAreaGeojson(Object areaGeojson) {
        this.areaGeojson = areaGeojson;
    }
}
