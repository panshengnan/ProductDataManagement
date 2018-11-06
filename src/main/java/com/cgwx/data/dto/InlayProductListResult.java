package com.cgwx.data.dto;

import java.util.List;

public class InlayProductListResult {
    private long totalItems;
    private int totalPageNum;
    private List<InlayProductList> productQueryList;


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

    public List<InlayProductList> getProductQueryList() {
        return productQueryList;
    }

    public void setProductQueryList(List<InlayProductList> productQueryList) {
        this.productQueryList = productQueryList;
    }
}
