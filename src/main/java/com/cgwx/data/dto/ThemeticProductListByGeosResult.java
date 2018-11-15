package com.cgwx.data.dto;

import java.util.List;

public class ThemeticProductListByGeosResult {
    List<ThemeticProductSimpleInfo> themeticProductSimpleInfoList;
    String productId;
    String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    List<Industry> industryList;

    public void setThemeticProductSimpleInfoList(List<ThemeticProductSimpleInfo> themeticProductSimpleInfoList) {
        this.themeticProductSimpleInfoList = themeticProductSimpleInfoList;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setIndustryList(List<Industry> industryList) {
        this.industryList = industryList;
    }

    public List<ThemeticProductSimpleInfo> getThemeticProductSimpleInfoList() {
        return themeticProductSimpleInfoList;
    }

    public String getProductId() {
        return productId;
    }

    public List<Industry> getIndustryList() {
        return industryList;
    }
}
