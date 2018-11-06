package com.cgwx.data.dto;

import java.util.Date;
import java.util.List;

public class ThemeticProductDetail {

    private String productId;

    private String themeticProductName;

    private String industry;

//    private String parentDirectory;

    private String countOfPeriods;

    private  String productDescription;

    private String clientName;

    private String delieverName;

    private Date delieverTime;

    private List<SinglePeriodThemeticProductDetail> singlePeriodThemeticProductDetail;


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getThemeticProductName() {
        return themeticProductName;
    }

    public void setThemeticProductName(String themeticProductName) {
        this.themeticProductName = themeticProductName == null ? null : themeticProductName.trim();
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    public String getCountOfPeriods() {
        return countOfPeriods;
    }

    public void setCountOfPeriods(String countOfPeriods) {
        this.countOfPeriods = countOfPeriods;
    }

    public List<SinglePeriodThemeticProductDetail> getSinglePeriodThemeticProductDetail(){
        return singlePeriodThemeticProductDetail;
    }
    public void setSinglePeriodThemeticProductDetail(List<SinglePeriodThemeticProductDetail> singlePeriodThemeticProductDetail){
        this.singlePeriodThemeticProductDetail=singlePeriodThemeticProductDetail;
    }

    public String getProductDescription(){return productDescription;}
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription == null ? null : productDescription.trim();
    }
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName == null ? null : clientName.trim();
    }

    public String getDelieverName() {
        return delieverName;
    }

    public void setDelieverName(String delieverName) {
        this.delieverName = delieverName == null ? null : delieverName.trim();
    }

    public Date getDelieverTime() {
        return delieverTime;
    }

    public void setDelieverTime(Date delieverTime) {
        this.delieverTime = delieverTime == null ? null : delieverTime;
    }


}
