package com.cgwx.data.entity;

public class PdmSensorInfo {
    private int sensorCode;

    private String sensorDescription;

    public int getSensorCode() {
        return sensorCode;
    }

    public void setSensorCode(int sensorCode) {
        this.sensorCode = sensorCode;
    }

    public String getSensorDescription() {
        return sensorDescription;
    }

    public void setSensorDescription(String sensorDescription) {
        this.sensorDescription = sensorDescription == null ? null : sensorDescription.trim();
    }
}