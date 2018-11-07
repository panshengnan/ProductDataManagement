package com.cgwx.service.impl;

import com.cgwx.dao.*;
import com.cgwx.data.dto.*;
import com.cgwx.data.entity.*;
import com.cgwx.service.IMetadataService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class MetadataServiceImpl implements IMetadataService {
    @Autowired
    PdmThemeticProductInfoMapper pdmThemeticProductInfoMapper;

    @Autowired
    PdmThemeticProductDetailInfoMapper pdmThemeticProductDetailInfoMapper;

    @Autowired
    PdmOrthoProductInfoMapper pdmOrthoProductInfoMapper;

    @Autowired
    PdmProductInfoMapper pdmProductInfoMapper;

    @Autowired
    PdmInlayProductInfoMapper pdmInlayProductInfoMapper;

    @Autowired
    PdmSubdivisionProductInfoMapper pdmSubdivisionProductInfoMapper;

    @Autowired
    PdmProductTypeInfoMapper pdmProductTypeInfoMapper;

    @Autowired
    IProductDownloadServiceImpl iProductDownloadService;

    @Override
   //単期产品详情
    public SinglePeriodThemeticProductDetail getSinglePeriodThemeticProductDetail(String productId, String singlePeriodProductId) {  //方法在userRequirementService中定义
        PdmThemeticProductDetailInfo themeticProductDetailPart2=pdmThemeticProductDetailInfoMapper.selectThemeticProductDetailByProductIdPart2(singlePeriodProductId,productId);
        PdmThemeticProductInfo themeticProductDetailPart1= pdmThemeticProductInfoMapper.selectThemeticProductDetailPart1ByProductId(productId);

        SinglePeriodThemeticProductDetail singlePeriodThemeticProductDetail =new SinglePeriodThemeticProductDetail();
        singlePeriodThemeticProductDetail.setSinglePeriodProductId(singlePeriodProductId);
        //System.err.print(" "+themeticProductDetailPart2.getProducer());
        singlePeriodThemeticProductDetail.setImageGeo(themeticProductDetailPart2.getImageGeo().toString());
        singlePeriodThemeticProductDetail.setProducer(themeticProductDetailPart2.getProducer());
        singlePeriodThemeticProductDetail.setSatellite(themeticProductDetailPart2.getSatellite());
        singlePeriodThemeticProductDetail.setSensor(themeticProductDetailPart2.getSensor());
        singlePeriodThemeticProductDetail.setSinglePeriodProductName(themeticProductDetailPart2.getSinglePeriodProductName());
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if(themeticProductDetailPart2.getImagingTime()==null)
        {
            singlePeriodThemeticProductDetail.setCenterImagingTime(null);
        }
        else
        {
            String setImagingtime=timeFormat.format(themeticProductDetailPart2.getImagingTime());
            singlePeriodThemeticProductDetail.setCenterImagingTime(setImagingtime);
        }

        if(themeticProductDetailPart2.getProduceTime()==null)
        {
            singlePeriodThemeticProductDetail.setProduceTime(null);
        }
        else
        {
            String setProducetime=timeFormat.format(themeticProductDetailPart2.getProduceTime());
            singlePeriodThemeticProductDetail.setProduceTime(setProducetime);
        }
        //singlePeriodThemeticProductDetail.setProduceTime(timeFormat.format(themeticProductDetailPart2.getProduceTime()));
        singlePeriodThemeticProductDetail.setSizeOfTif(themeticProductDetailPart2.getSizeOfTif());

        String path =themeticProductDetailPart1.getParentDirectory();




        //获取文件列表和对应的URL
        String path3 ="C:\\pdm_bak\\专题产品\\长春市201309热岛效应\\1";
        List<FileUrl> themeticUrlList = getFileListAndUrl(productId,singlePeriodProductId);
        singlePeriodThemeticProductDetail.setFileListAndUrl(themeticUrlList);

//        //获取分析报告路径
//        String path1="C:\\pdm_bak\\专题产品\\长春市201309热岛效应\\长春市201309热岛效应.pdf";
//        singlePeriodThemeticProductDetail.setAnalysisReportUrl(path1);
//        //获取全部文件URL
//        String path4 ="C:\\pdm_bak\\专题产品\\长春市201309热岛效应";
//        singlePeriodThemeticProductDetail.setAllFileDownloadUrl(path4);

//        //获取缩略图url
//        String path5="C:\\pdm_bak\\专题产品\\长春市201309热岛效应";
//        singlePeriodThemeticProductDetail.setThumbnailUrl(path5);
        for(int a=0;a<themeticUrlList.size();a++) {
            if(themeticUrlList.get(a).getFileName().contains("jpg"))
            {
                System.out.println("removejpg"+themeticUrlList.get(a).getFileName());
                singlePeriodThemeticProductDetail.setThumbnailUrl(themeticUrlList.get(a).getFileUrl());
                themeticUrlList.remove(a);
                break;
            }
        }

        return singlePeriodThemeticProductDetail;
    }

//    @Override
//    public  List<FileUrl> getFileListAndUrl(String productId) {
//       // List<PdmProductStoreLinkInfo> productLinkList = iProductDownloadService.getProductLinkList(productId);
//        List<FileUrl> urlList = new ArrayList<>();
//        List<PdmProductStoreLinkInfo> productLinkList = iProductDownloadService.getProductLinkList(productId,null);
//        for(int a=0;a<productLinkList.size();a++) {
//            FileUrl fileUrl = new FileUrl();
//            fileUrl.setFileName(productLinkList.get(a).getFileName());
//            fileUrl.setFileUrl(productLinkList.get(a).getStoreLink());
//            urlList.add(fileUrl);
//        }
//        return  urlList;
//    }
    @Override
    //获取文件列表和URL
    public  List<FileUrl> getFileListAndUrl (String productId,String singlePeriodProductId){
//        File pf=new File(path);
//        List<String> fileList=new ArrayList<>();
//        String [] fileString=pf.list();
//        for(String s:fileString) {
//            fileList.add(s);
//        }
        List<PdmProductStoreLinkInfo> productLinkList = iProductDownloadService.getProductLinkList(productId,singlePeriodProductId);

        List<FileUrl> urlList = new ArrayList<>();
        //String url="C:\\pdm_bak\\专题产品\\长春市201309热岛效应";
        for(int a=0;a<productLinkList.size();a++) {
            FileUrl fileUrl = new FileUrl();
            fileUrl.setFileName(productLinkList.get(a).getFileName());
            fileUrl.setFileUrl(productLinkList.get(a).getStoreLink());
            urlList.add(fileUrl);
        }
        return urlList;
    }
    @Override
    //专题产品详情
    public ThemeticProductDetail getThemeticProductDetail (String productId, List<String> singlePeriodProductIdList) {
       // System.err.println("id"+productId);
        ThemeticProductDetail themeticProductDetail =new ThemeticProductDetail();
        PdmThemeticProductInfo themeticProductDetailPart1= pdmThemeticProductInfoMapper.selectThemeticProductDetailPart1ByProductId(productId);
        themeticProductDetail.setProductId(themeticProductDetailPart1.getProductId());
        themeticProductDetail.setThemeticProductName(themeticProductDetailPart1.getThemeticProductName());
        themeticProductDetail.setIndustry(themeticProductDetailPart1.getIndustry());
       // System.err.println(themeticProductDetailPart1.getProductId()+" -"+themeticProductDetailPart1.getThemeticProductName()+" -"+themeticProductDetailPart1.getIndustry());
        themeticProductDetail.setCountOfPeriods(pdmThemeticProductDetailInfoMapper.countSinglePeriodThemeticProductId(productId).toString());
       // System.err.println("peri"+themeticProductDetail.getCountOfPeriods());
        themeticProductDetail.setProductDescription(pdmProductInfoMapper.selectProductDescriptionByProductId(productId));

        themeticProductDetail.setClientName(themeticProductDetailPart1.getClientName());
        themeticProductDetail.setDelieverName(themeticProductDetailPart1.getDelieverName());
        themeticProductDetail.setDelieverTime(themeticProductDetailPart1.getDelieverTime());
        //获取分析报告路径
        //String parentDirect=iProductDownloadService.getEntityFilePath(productId);
        String path1="C:\\pdm_bak\\专题产品\\长春市201309热岛效应\\长春市201309热岛效应.pdf";
        themeticProductDetail.setAnalysisReportUrl(path1);
        //获取全部文件URL
        String path4 ="C:\\pdm_bak\\专题产品\\长春市201309热岛效应";
        themeticProductDetail.setAllFileDownloadUrl(path4);
        Integer size=pdmThemeticProductDetailInfoMapper.countSinglePeriodThemeticProductId(productId);
       // System.err.println("size"+size);
        List<SinglePeriodThemeticProductDetail> list =new ArrayList<>();
        for (int count = 0; count <size; count++){
           list.add(getSinglePeriodThemeticProductDetail(productId,singlePeriodProductIdList.get(count)));
        }
        themeticProductDetail.setSinglePeriodThemeticProductDetail(list);
        return themeticProductDetail;

    }

    @Override
    //正射产品详情
   public OrthoProductDetail getOrthoProductDetail(String productId){
       PdmOrthoProductInfo pdmOrthoProductInfo = pdmOrthoProductInfoMapper.selectOrthoProductDetailByProductId(productId);
       OrthoProductDetail orthoProductDetail=new OrthoProductDetail();
       orthoProductDetail.setProductId(pdmOrthoProductInfo.getProductId());
       orthoProductDetail.setOrthoProductName(pdmOrthoProductInfo.getOrthoProductName());
       orthoProductDetail.setImageGeo(pdmOrthoProductInfo.getImageGeo().toString());
       orthoProductDetail.setProducer(pdmOrthoProductInfo.getProducer());
       orthoProductDetail.setGeographicInfo(pdmOrthoProductInfo.getGeographicInfo());
       orthoProductDetail.setSatellite(pdmOrthoProductInfo.getSatellite());
       orthoProductDetail.setSensor(pdmOrthoProductInfo.getSensor());
       if(pdmOrthoProductInfo.getResolution()==null)
       {
           orthoProductDetail.setResolution(null);
       }
       else
       {
           orthoProductDetail.setResolution(pdmOrthoProductInfo.getResolution().toString());
       }

       orthoProductDetail.setImageBreath(pdmOrthoProductInfo.getImageBreath());
       SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       if(pdmOrthoProductInfo.getCaptureTime()==null)
       {
           orthoProductDetail.setCaptureTime(null);
       }
       else
       {
           orthoProductDetail.setCaptureTime(timeFormat.format(pdmOrthoProductInfo.getCaptureTime()));
       }

       orthoProductDetail.setSizeOfTif(pdmOrthoProductInfo.getSizeOfTif());
       orthoProductDetail.setClientName(pdmOrthoProductInfo.getClientName());
       orthoProductDetail.setDelieverName(pdmOrthoProductInfo.getDelieverName());
       orthoProductDetail.setDelieverTime(pdmOrthoProductInfo.getDelieverTime());
       orthoProductDetail.setProductType("ortho");

       String path =pdmOrthoProductInfo.getOrthoProductDirectory();
       //获取分析报告路径，后续需要改
//       String path1="C:\\pdm_bak\\专题产品\\长春市201309热岛效应\\长春市201309热岛效应.pdf";
//       orthoProductDetail.setAnalysisReportUrl(path1);
//
//       //获取文件列表和URL
//       String path3 ="C:\\pdm_bak\\专题产品\\长春市201309热岛效应";
//       List<FileUrl> orthoUrlList = getFileListAndUrl(path3);
//
//       orthoProductDetail.setFileListAndUrl(orthoUrlList);
//
//
       //获取全部文件URL
       String path4 ="C:\\pdm_bak\\专题产品\\长春市201309热岛效应";
       orthoProductDetail.setAllFileDownloadUrl(path4);
//
//       //获取缩略图url
//       String path5="C:\\pdm_bak\\专题产品\\长春市201309热岛效应";
//       orthoProductDetail.setThumbnailUrl(path5);
        List<FileUrl> orthoUrlList = getFileListAndUrl(productId,null);

        for(int a=0;a<orthoUrlList.size();a++)
        {
            if(orthoUrlList.get(a).getFileName().contains("jpg"))
            {
                System.out.println("removejpg"+orthoUrlList.get(a).getFileName());
                orthoProductDetail.setThumbnailUrl(orthoUrlList.get(a).getFileUrl());
                orthoUrlList.remove(a);

            }else if(orthoUrlList.get(a).getFileName().contains("pdf"))
            {
                System.out.println("removepdf"+orthoUrlList.get(a).getFileName());
                orthoProductDetail.setAnalysisReportUrl(orthoUrlList.get(a).getFileUrl());
                orthoUrlList.remove(a);

            }

        }
        orthoProductDetail.setFileListAndUrl(orthoUrlList);
       return orthoProductDetail;
   }

   //镶嵌产品
   @Override
    public InlayProductDetail getInlayProductDetail(String productId) {

        InlayProductDetail inlayProductDetail=new InlayProductDetail();
        PdmInlayProductInfo pdmInlayProductInfo = pdmInlayProductInfoMapper.selectInlayProductDetailByProductId(productId);
        inlayProductDetail.setProductId(pdmInlayProductInfo.getProductId());
        inlayProductDetail.setInlayProductName(pdmInlayProductInfo.getInlayProductName());
        inlayProductDetail.setImageGeo(pdmInlayProductInfo.getImageGeo().toString());
        inlayProductDetail.setProducer(pdmInlayProductInfo.getProducer());
        inlayProductDetail.setGeographicInfo(pdmInlayProductInfo.getGeographicInfo());
        inlayProductDetail.setSizeOfTif(pdmInlayProductInfo.getSizeOfTif());
        inlayProductDetail.setClientName(pdmInlayProductInfo.getClientName());
        inlayProductDetail.setDelieverName(pdmInlayProductInfo.getDelieverName());
        inlayProductDetail.setDelieverTime(pdmInlayProductInfo.getDelieverTime());
        inlayProductDetail.setProductType("inlay");

        String path =pdmInlayProductInfo.getInlayProductDirectory();
        //获取分析报告路径，后续需要改
//        String path1="C:\\pdm_bak\\专题产品\\长春市201309热岛效应\\长春市201309热岛效应.pdf";
//        inlayProductDetail.setAnalysisReportUrl(path1);
//
//        //获取文件列表和URL
//        String path3 ="C:\\pdm_bak\\专题产品\\长春市201309热岛效应";
//        List<FileUrl> inlayUrlList = getFileListAndUrl(path3);
//
//        inlayProductDetail.setFileListAndUrl(inlayUrlList);
//
//
        //获取全部文件URL
        String path4 ="C:\\pdm_bak\\专题产品\\长春市201309热岛效应";
        inlayProductDetail.setAllFileDownloadUrl(path4);
//
//        //获取缩略图url
//        String path5="C:\\pdm_bak\\专题产品\\长春市201309热岛效应";
//        inlayProductDetail.setThumbnailUrl(path5);
       List<FileUrl> inlayUrlList = getFileListAndUrl(productId,null);

       for(int a=0;a<inlayUrlList.size();a++) {
           if(inlayUrlList.get(a).getFileName().contains("jpg"))
           {
               System.out.println("removejpg"+inlayUrlList.get(a).getFileName());
               inlayProductDetail.setThumbnailUrl(inlayUrlList.get(a).getFileUrl());
               inlayUrlList.remove(a);

           }else if(inlayUrlList.get(a).getFileName().contains("pdf"))
           {
               System.out.println("removepdf"+inlayUrlList.get(a).getFileName());
               inlayProductDetail.setAnalysisReportUrl(inlayUrlList.get(a).getFileUrl());
               inlayUrlList.remove(a);
           }

       }

        return inlayProductDetail;

    }

    //分幅产品
    @Override
    public SubdivisionProductDetail getSubdivisionProductDetail(String productId) {
       PdmSubdivisionProductInfo pdmSubdivisionProductInfo=pdmSubdivisionProductInfoMapper.selectSubdivisionProductDetailByProductId(productId);
       SubdivisionProductDetail subdivisionProductDetail =new SubdivisionProductDetail();
       subdivisionProductDetail.setProductId(productId);
       subdivisionProductDetail.setSubdivisionProductName(pdmSubdivisionProductInfo.getSubdivisionProductName());
       subdivisionProductDetail.setImageGeo(pdmSubdivisionProductInfo.getImageGeo().toString());
       subdivisionProductDetail.setNumberOfTif(pdmSubdivisionProductInfo.getNumberOfTif().toString());
       subdivisionProductDetail.setIndustry(pdmSubdivisionProductInfo.getIndustry());
       subdivisionProductDetail.setProducer(pdmSubdivisionProductInfo.getProducer());
       subdivisionProductDetail.setClientName(pdmSubdivisionProductInfo.getClientName());
       subdivisionProductDetail.setDelieverName(pdmSubdivisionProductInfo.getDelieverName());
       subdivisionProductDetail.setDelieverTime(pdmSubdivisionProductInfo.getDelieverTime());
       if(pdmSubdivisionProductInfo.getResolution()==null)
       {
           subdivisionProductDetail.setResolution(null);
       }
       else
       {
           subdivisionProductDetail.setResolution(pdmSubdivisionProductInfo.getResolution().toString());
       }

       subdivisionProductDetail.setGeographicInfo(pdmSubdivisionProductInfo.getGeographicInfo());
       subdivisionProductDetail.setProductType("subdivision");

       String path =pdmSubdivisionProductInfo.getSubdivisionProductDirectory();
        //获取分析报告路径，后续需要改
//        String path1="C:\\pdm_bak\\专题产品\\长春市201309热岛效应\\长春市201309热岛效应.pdf";
//        subdivisionProductDetail.setAnalysisReportUrl(path1);
//
//        //获取文件列表和URL
//        String path3 ="C:\\pdm_bak\\专题产品\\长春市201309热岛效应";
//        List<FileUrl> subdivisionUrlList = getFileListAndUrl(path3);
//
//        subdivisionProductDetail.setFileListAndUrl(subdivisionUrlList);
//
//
        //获取全部文件URL
        String path4 ="C:\\pdm_bak\\专题产品\\长春市201309热岛效应";
        subdivisionProductDetail.setAllFileDownloadUrl(path4);
//
//        //获取缩略图url
//        String path5="C:\\pdm_bak\\专题产品\\长春市201309热岛效应";
//        subdivisionProductDetail.setThumbnailUrl(path5);
        List<FileUrl> subdivisionUrlList = getFileListAndUrl(productId,null);

        for(int a=0;a<subdivisionUrlList.size();a++) {
            if(subdivisionUrlList.get(a).getFileName().contains("jpg"))
            {
                System.out.println("removejpg"+subdivisionUrlList.get(a).getFileName());
                subdivisionProductDetail.setThumbnailUrl(subdivisionUrlList.get(a).getFileUrl());
                subdivisionUrlList.remove(a);

            }else if(subdivisionUrlList.get(a).getFileName().contains("pdf"))
            {
                System.out.println("removepdf"+subdivisionUrlList.get(a).getFileName());
                subdivisionProductDetail.setAnalysisReportUrl(subdivisionUrlList.get(a).getFileUrl());
                subdivisionUrlList.remove(a);
            }

        }
    return subdivisionProductDetail;

    }


    //获取产品列表
    @Override
    public ProductQueryListResult getProductList(ProductQueryCri cri){

        String productName=cri.getProductName();
        String productDescription=cri.getProductDescription();
        String orderby=cri.getOrderby();
        String type=cri.getProductType();
        List<ProductQueryList> list = new ArrayList<>();
        System.out.print("type" + cri.getProductType());
        if(type.equals("")) {
            List<ProductQueryList> listPart1 = pdmProductInfoMapper.selectThemeticProductByCondition(productName, productDescription,orderby);
            list.addAll(listPart1);
            List<ProductQueryList> listPart2=pdmProductInfoMapper.selectOrthoProductByCondition(productName, productDescription,orderby);
            list.addAll(listPart2);
            List<ProductQueryList> listPart3=pdmProductInfoMapper.selectInlayProductByCondition(productName, productDescription,orderby);
            list.addAll(listPart3);
            List<ProductQueryList> listPart4=pdmProductInfoMapper.selectSubdivisionProductByCondition(productName, productDescription,orderby);
            list.addAll(listPart4);

            }
            else{
            if (type.equals("themetic")) {

                list = pdmProductInfoMapper.selectThemeticProductByCondition(productName, productDescription, orderby);

            }
            else if (type.equals("ortho")){
                list = pdmProductInfoMapper.selectOrthoProductByCondition(productName, productDescription,  orderby);

            }
            else if(type.equals("inlay")){
                list = pdmProductInfoMapper.selectInlayProductByCondition(productName, productDescription,  orderby);

            }
            else if(type.equals("subdivision")){
                list = pdmProductInfoMapper.selectSubdivisionProductByCondition(productName, productDescription,  orderby);

            }
            else{
                System.out.print("wrong product Type");
            }

            }



        PageInfo<ProductQueryList> pageInfo = new PageInfo<>(list);

        ProductQueryListResult productQueryListResult = new ProductQueryListResult();

        productQueryListResult.setTotalItems(pageInfo.getTotal());
        productQueryListResult.setTotalPageNum(pageInfo.getPages());
        productQueryListResult.setProductQueryList(list);
        return productQueryListResult;

    }
    public ThemeticProductListResult getThemeticProductList (ThemeticProductCri themeticProductCri) {

        String productName=themeticProductCri.getProductName();
        String orderby=themeticProductCri.getOrderby();
        String satellite=themeticProductCri.getSatellite();
        String industry=themeticProductCri.getIndustry();
        String sensor=themeticProductCri.getSensor();
        String clientName=themeticProductCri.getClientName();
        String delieverName=themeticProductCri.getDelieverName();
        List<ThemeticProductList> themeticProductDetailList = pdmThemeticProductInfoMapper.selectThemeticProductByCondition(productName,orderby,satellite,industry,sensor,clientName,delieverName);

        PageInfo<ThemeticProductList> pageInfo = new PageInfo<>(themeticProductDetailList);

        ThemeticProductListResult themeticProductListResult = new ThemeticProductListResult();

        themeticProductListResult.setTotalItems(pageInfo.getTotal());
        themeticProductListResult.setTotalPageNum(pageInfo.getPages());
        themeticProductListResult.setProductQueryList(themeticProductDetailList);
        return themeticProductListResult;

    }
    public OrthoProductListResult getOrthoProductList (OrthoProductCri orthoProductCri) {

        String productName = orthoProductCri.getProductName();
        String orderby = orthoProductCri.getOrderby();
        String satellite = orthoProductCri.getSatellite();
        String sensor = orthoProductCri.getSensor();
        String clientName = orthoProductCri.getClientName();
        String delieverName = orthoProductCri.getDelieverName();
        BigDecimal resolution = orthoProductCri.getResolution();
        String imageBreath = orthoProductCri.getImageBreath();
        List<OrthoProductList> orthoProductList = pdmOrthoProductInfoMapper.selectOrthoProductByCondition(productName, orderby, satellite, sensor, clientName, delieverName, resolution, imageBreath);

        PageInfo<OrthoProductList> pageInfo = new PageInfo<>(orthoProductList);

        OrthoProductListResult orthoProductListResult = new OrthoProductListResult();

        orthoProductListResult.setTotalItems(pageInfo.getTotal());
        orthoProductListResult.setTotalPageNum(pageInfo.getPages());
        orthoProductListResult.setProductQueryList(orthoProductList);
        return orthoProductListResult;
    }

    public InlayProductListResult getInlayProductList (InlayProductCri inlayProductCri) {

        String productName = inlayProductCri.getProductName();
        String orderby = inlayProductCri.getOrderby();
        String clientName = inlayProductCri.getClientName();
        String delieverName = inlayProductCri.getDelieverName();
        List<InlayProductList> inlayProductList = pdmInlayProductInfoMapper.selectInlayProductByCondition(productName, orderby, clientName, delieverName);

        PageInfo<InlayProductList> pageInfo = new PageInfo<>(inlayProductList);

        InlayProductListResult inlayProductListResult = new InlayProductListResult();

        inlayProductListResult.setTotalItems(pageInfo.getTotal());
        inlayProductListResult.setTotalPageNum(pageInfo.getPages());
        inlayProductListResult.setProductQueryList(inlayProductList);
        return inlayProductListResult;
    }

    public SubdivisionProductListResult getSubdivisionProductList (SubdivisionProductCri subdivisionProductCri) {

        String productName = subdivisionProductCri.getProductName();
        String orderby = subdivisionProductCri.getOrderby();
        String clientName = subdivisionProductCri.getClientName();
        String delieverName = subdivisionProductCri.getDelieverName();
        BigDecimal resolution = subdivisionProductCri.getResolution();
        String industry=subdivisionProductCri.getIndustry();

        List<SubdivisionProductList> subdivisionProductList = pdmSubdivisionProductInfoMapper.selectSubdivisionProductByCondition(productName, orderby, clientName, delieverName,resolution,industry);

        PageInfo<SubdivisionProductList> pageInfo = new PageInfo<>(subdivisionProductList);

        SubdivisionProductListResult subdivisionProductListResult = new SubdivisionProductListResult();

        subdivisionProductListResult.setTotalItems(pageInfo.getTotal());
        subdivisionProductListResult.setTotalPageNum(pageInfo.getPages());
        subdivisionProductListResult.setProductQueryList(subdivisionProductList);
        return subdivisionProductListResult;
    }


    }

