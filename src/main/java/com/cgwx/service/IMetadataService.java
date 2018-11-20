package com.cgwx.service;

import com.cgwx.data.dto.*;

import java.util.List;

public interface IMetadataService {

    SinglePeriodThemeticProductDetail getSinglePeriodThemeticProductDetail(String productId, String singlePeriodProductId);
    ThemeticProductDetail getThemeticProductDetail(String productId, List<String> singlePeriodProductIdList);
    OrthoProductDetail getOrthoProductDetail(String productId);
    InlayProductDetail getInlayProductDetail(String productId);
    List<FileUrl> getFileListAndUrl(String productId,String singlePeriodProductId);
    //List<FileUrl> getFileListAndUrl(String productId);
    SubdivisionProductDetail getSubdivisionProductDetail(String productId);
    ProductQueryListResult getProductList(ProductQueryCri cri);
    ThemeticProductListResult getThemeticProductList (ThemeticProductCri themeticProductCri);
    OrthoProductListResult getOrthoProductList (OrthoProductCri orthoProductCri);
    InlayProductListResult getInlayProductList (InlayProductCri inlayProductCri);
    SubdivisionProductListResult getSubdivisionProductList (SubdivisionProductCri subdivisionProductCri) ;
    List<ThemeticProductSimpleInfo> getThemeticSimpleProductlist(Object geo, String producer);
    List<ThemeticProductSimpleInfo> testgetSimpleProductlist(int type);
    List<String> getProductIdlist(String clientname,String description);
    List<String> removeRepeat(List<String> stringList);
    void mergeThemeticSimpleInfoListByProductIdlist(List<ThemeticProductSimpleInfo> themeticProductSimpleInfoList, List<String>productIdlist);
    List<String> getProductIdlistFromIndustry(int level1,int level2);
    List<String> getProductIdlistByIndustryList( List<Industry> industryList);
    void printThemeticSimpleInfoList(List<ThemeticProductSimpleInfo> themeticProductSimpleInfoList);
    List<ThemeticProductListByGeosResult> packetSingleThemeticProductToThemeticProduct(List<ThemeticProductSimpleInfo> themeticProductSimpleInfoList);
    List<Industry> getIndustryByProductid(String productId);
    List<AdvanceProductSimpleInfo> getAdvanceProductSimpleInfoList(String producer,Object image_geo,String clientName,String description);
}
