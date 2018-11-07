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

    }
