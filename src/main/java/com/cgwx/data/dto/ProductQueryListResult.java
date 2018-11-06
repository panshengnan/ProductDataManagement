package com.cgwx.data.dto;

import java.util.List;

public class ProductQueryListResult {
    private long totalItems;
    private int totalPageNum;
    private List<ProductQueryList> productQueryList;


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

    public List<ProductQueryList> getProductQueryList() {
        return productQueryList;
    }

    public void setProductQueryList(List<ProductQueryList> productQueryList) {
        this.productQueryList = productQueryList;
    }
}
