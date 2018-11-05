package com.cgwx.data.entity;

public class PdmIndustryTopographicFeaturesSegmentationInfoIdSeq {
    private String sequenceName;

    private Long lastValue;

    private Long startValue;

    private Long incrementBy;

    private Long maxValue;

    private Long minValue;

    private Long cacheValue;

    private Long logCnt;

    private Boolean isCycled;

    private Boolean isCalled;

    public String getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName == null ? null : sequenceName.trim();
    }

    public Long getLastValue() {
        return lastValue;
    }

    public void setLastValue(Long lastValue) {
        this.lastValue = lastValue;
    }

    public Long getStartValue() {
        return startValue;
    }

    public void setStartValue(Long startValue) {
        this.startValue = startValue;
    }

    public Long getIncrementBy() {
        return incrementBy;
    }

    public void setIncrementBy(Long incrementBy) {
        this.incrementBy = incrementBy;
    }

    public Long getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Long maxValue) {
        this.maxValue = maxValue;
    }

    public Long getMinValue() {
        return minValue;
    }

    public void setMinValue(Long minValue) {
        this.minValue = minValue;
    }

    public Long getCacheValue() {
        return cacheValue;
    }

    public void setCacheValue(Long cacheValue) {
        this.cacheValue = cacheValue;
    }

    public Long getLogCnt() {
        return logCnt;
    }

    public void setLogCnt(Long logCnt) {
        this.logCnt = logCnt;
    }

    public Boolean getIsCycled() {
        return isCycled;
    }

    public void setIsCycled(Boolean isCycled) {
        this.isCycled = isCycled;
    }

    public Boolean getIsCalled() {
        return isCalled;
    }

    public void setIsCalled(Boolean isCalled) {
        this.isCalled = isCalled;
    }
}