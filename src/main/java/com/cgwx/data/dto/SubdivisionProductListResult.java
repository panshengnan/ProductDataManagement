package com.cgwx.data.dto;

import java.util.List;

public class SubdivisionProductListResult {
    private long totalItems;
    private int totalPageNum;
    private List<SubdivisionProductList> productQueryList;


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

    public List<SubdivisionProductList> getProductQueryList() {
        return productQueryList;
    }

    public void setProductQueryList(List<SubdivisionProductList> productQueryList) {
        this.productQueryList = productQueryList;
    }
}
