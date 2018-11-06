package com.cgwx.data.dto;

import java.util.List;

public class OrthoProductListResult {
    private long totalItems;
    private int totalPageNum;
    private List<OrthoProductList> productQueryList;


    public int getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public List<OrthoProductList> getProductQueryList() {
        return productQueryList;
    }

    public void setProductQueryList(List<OrthoProductList> productQueryList) {
        this.productQueryList = productQueryList;
    }
}
