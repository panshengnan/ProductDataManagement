package com.cgwx.data.dto;

import java.util.List;

public class AdvanceProductSimpleInfoResult {
    private long totalItems;
    private int totalPageNum;
    private List<AdvanceProductSimpleInfo> productQueryList;

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public List<AdvanceProductSimpleInfo> getProductQueryList() {
        return productQueryList;
    }

    public void setProductQueryList(List<AdvanceProductSimpleInfo> productQueryList) {
        this.productQueryList = productQueryList;
    }
}
