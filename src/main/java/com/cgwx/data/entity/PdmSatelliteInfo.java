package com.cgwx.data.entity;

public class PdmSatelliteInfo {
    private int satelliteCode;

    private String satelliteDescription;

    public int getSatelliteCode() {
        return satelliteCode;
    }

    public void setSatelliteCode(int satelliteCode) {
        this.satelliteCode = satelliteCode;
    }

    public String getSatelliteDescription() {
        return satelliteDescription;
    }

    public void setSatelliteDescription(String satelliteDescription) {
        this.satelliteDescription = satelliteDescription == null ? null : satelliteDescription.trim();
    }
}