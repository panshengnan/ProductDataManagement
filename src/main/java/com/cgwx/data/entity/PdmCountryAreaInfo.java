package com.cgwx.data.entity;

import java.math.BigDecimal;

public class PdmCountryAreaInfo {
    private Integer nationId;

    private String iso;

    private String nationName;

    private BigDecimal leng;

    private BigDecimal areaKm2;

    private Object geo;

    private String nationChineseName;

    public Integer getNationId() {
        return nationId;
    }

    public void setNationId(Integer nationId) {
        this.nationId = nationId;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso == null ? null : iso.trim();
    }

    public String getNationName() {
        return nationName;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName == null ? null : nationName.trim();
    }

    public BigDecimal getLeng() {
        return leng;
    }

    public void setLeng(BigDecimal leng) {
        this.leng = leng;
    }

    public BigDecimal getAreaKm2() {
        return areaKm2;
    }

    public void setAreaKm2(BigDecimal areaKm2) {
        this.areaKm2 = areaKm2;
    }

    public Object getGeo() {
        return geo;
    }

    public void setGeo(Object geo) {
        this.geo = geo;
    }

    public String getNationChineseName() {
        return nationChineseName;
    }

    public void setNationChineseName(String nationChineseName) {
        this.nationChineseName = nationChineseName == null ? null : nationChineseName.trim();
    }
}