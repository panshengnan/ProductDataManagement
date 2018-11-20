package com.cgwx.data.entity;

public class PdmDomesticAreaInfo {
    private Integer areaId;

    private Integer parentAreaId;

    private String areaName;

    private Integer areaLevel;

    private Object areaGeo;

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getParentAreaId() {
        return parentAreaId;
    }

    public void setParentAreaId(Integer parentAreaId) {
        this.parentAreaId = parentAreaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public Integer getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(Integer areaLevel) {
        this.areaLevel = areaLevel;
    }

    public Object getAreaGeo() {
        return areaGeo;
    }

    public void setAreaGeo(Object areaGeo) {
        this.areaGeo = areaGeo;
    }

}