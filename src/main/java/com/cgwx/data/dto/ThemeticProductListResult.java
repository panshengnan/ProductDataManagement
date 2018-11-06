package com.cgwx.data.dto;

import java.util.List;

public class ThemeticProductListResult {
    private long totalItems;
    private int totalPageNum;
    private List<ThemeticProductList> productQueryList;


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

    public List<ThemeticProductList> getProductQueryList() {
        return productQueryList;
    }

    public void setProductQueryList(List<ThemeticProductList> productQueryList) {
        this.productQueryList = productQueryList;
    }
}
