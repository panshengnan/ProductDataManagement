package com.cgwx.data.dto;

import java.util.Date;
import java.util.List;

public class ThemeticProductDetail {

    private String productId;

    private String themeticProductName;

    private  List<Industry> industry;

//    private String parentDirectory;

    private String countOfPeriods;

    public void setIndustry(List<Industry> industry) {
        this.industry = industry;
    }

    public List<Industry> getIndustry() {
        return industry;
    }

    private  String productDescription;

    private String clientName;

    private String deliverName;

    private Date deliverTime;
    private String analysisReportUrl;

    public void setDocAnalysisReportUrl(String docAnalysisReportUrl) {
        this.docAnalysisReportUrl = docAnalysisReportUrl;
    }

    public String getDocAnalysisReportUrl() {
        return docAnalysisReportUrl;
    }

    private String docAnalysisReportUrl;
    private String allFileDownloadUrl;

    public void setAnalysisReportUrl(String analysisReportUrl) {
        this.analysisReportUrl = analysisReportUrl;
    }

    public void setAllFileDownloadUrl(String allFileDownloadUrl) {
        this.allFileDownloadUrl = allFileDownloadUrl;
    }

    public String getAnalysisReportUrl() {
        return analysisReportUrl;
    }

    public String getAllFileDownloadUrl() {
        return allFileDownloadUrl;
    }

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

    public String getDeliverName() {
        return deliverName;
    }

    public void setDeliverName(String deliverName) {
        this.deliverName = deliverName == null ? null : deliverName.trim();
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime == null ? null : deliverTime;
    }


}
