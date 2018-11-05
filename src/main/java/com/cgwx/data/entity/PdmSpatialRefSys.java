package com.cgwx.data.entity;

public class PdmSpatialRefSys {
    private Integer srid;

    private String authName;

    private Integer authSrid;

    private String srtext;

    private String proj4text;

    public Integer getSrid() {
        return srid;
    }

    public void setSrid(Integer srid) {
        this.srid = srid;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName == null ? null : authName.trim();
    }

    public Integer getAuthSrid() {
        return authSrid;
    }

    public void setAuthSrid(Integer authSrid) {
        this.authSrid = authSrid;
    }

    public String getSrtext() {
        return srtext;
    }

    public void setSrtext(String srtext) {
        this.srtext = srtext == null ? null : srtext.trim();
    }

    public String getProj4text() {
        return proj4text;
    }

    public void setProj4text(String proj4text) {
        this.proj4text = proj4text == null ? null : proj4text.trim();
    }
}