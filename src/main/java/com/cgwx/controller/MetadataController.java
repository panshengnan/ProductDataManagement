package com.cgwx.controller;


import com.cgwx.aop.result.Result;
import com.cgwx.aop.result.ResultUtil;
import com.cgwx.dao.PdmProductInfoMapper;
import com.cgwx.dao.PdmProductTypeInfoMapper;
import com.cgwx.dao.PdmThemeticProductDetailInfoMapper;
import com.cgwx.data.dto.*;
import com.cgwx.service.IMetadataService;
import com.cgwx.service.impl.IProductArchiveServiceImpl;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@Controller
public class MetadataController
{

    @Autowired
    IMetadataService metadataService;
    @Autowired
    PdmThemeticProductDetailInfoMapper pdmThemeticProductDetailInfoMapper;
    @Autowired
    PdmProductInfoMapper pdmProductInfoMapper;
    @Autowired
    PdmProductTypeInfoMapper pdmProductTypeInfoMapper;
    @Autowired
    IProductArchiveServiceImpl iProductArchiveService;



    @RequestMapping(value = "/themeticProductDetail")  //专题产品详情
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public Result ThemeticProductDetail(@RequestParam(value = "productId", required = true) String productId) {

        List<String> singlePeriodProductIdList=pdmThemeticProductDetailInfoMapper.selecSinglePeriodThemeticProductList(productId);
       // System.err.println(singlePeriodProductIdList);
        ThemeticProductDetail multiPeriodThemeticProductDetail = metadataService.getThemeticProductDetail(productId,singlePeriodProductIdList);
        return ResultUtil.success(multiPeriodThemeticProductDetail);
    }



    @RequestMapping(value = "/advancedProductDetail")  //高级产品详情
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public Result advancedProductDetail(@RequestParam(value = "productId", required = true) String productId) {

        int productType= pdmProductInfoMapper.selectProductTypeByProductId(productId);
        System.out.print(productType);
        String  advancedProductType = pdmProductTypeInfoMapper.selectProductTypeDescriptionByProductType(productType);
        System.out.print(advancedProductType);
        //正射产品
        if(advancedProductType.equals("正射产品")) {
            OrthoProductDetail orthoProductDetail = metadataService.getOrthoProductDetail(productId);
            return ResultUtil.success(orthoProductDetail);
        }
        //镶嵌产品
        else if(advancedProductType.equals("镶嵌产品")){
            InlayProductDetail inlayProductDetail = metadataService.getInlayProductDetail(productId);
            return  ResultUtil.success(inlayProductDetail);

        }
        //分幅产品
        else if(advancedProductType.equals("分幅产品")){

            SubdivisionProductDetail subdivisionProductDetail = metadataService.getSubdivisionProductDetail(productId);
            return ResultUtil.success(subdivisionProductDetail);

        }
        else{
            return ResultUtil.success("wrong product type");
        }
    }


    @RequestMapping(value = "/productList")  //全部需求列表
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public Result getProductList(@RequestParam(value = "curPageNum", required = true) int curPageNum,
                                 @RequestParam(value = "maxResult", required = true) int maxResult,
                                 @RequestParam(value = "productType", required = true) String productType,
                                 @RequestParam(value = "productName", required = true) String productName,
                                 @RequestParam(value = "productDescription", required = true) String productDescription,
                                 @RequestParam(value = "orderby", required = true) String orderby) {

        ProductQueryCri cri = new ProductQueryCri();  //分页
        cri.setCurPageNum(curPageNum);
        cri.setMaxResult(maxResult);

        cri.setProductType(productType);
        cri.setProductName(productName);
        cri.setProductDescription(productDescription);
        cri.setOrderby(orderby);
        PageHelper.startPage(cri.getCurPageNum(), cri.getMaxResult());

        ProductQueryListResult productQueryListResult = metadataService.getProductList(cri);
        return ResultUtil.success(productQueryListResult);


    }

    @RequestMapping(value = "/themeticProductList")  //专题产品列表
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public Result getThemeticProductList(@RequestParam(value = "curPageNum", required = true) int curPageNum,
                                         @RequestParam(value = "maxResult", required = true) int maxResult,
                                         @RequestParam(value = "satellite", required = true) String satellite,
                                         @RequestParam(value = "industry", required = true) String industry,
                                         @RequestParam(value = "productName", required = true) String productName,
                                         @RequestParam(value = "sensor", required = true) String sensor,
                                         @RequestParam(value = "clientName", required = true) String clientName,
                                         @RequestParam(value = "delieverName", required = true) String delieverName,
                                         @RequestParam(value = "orderby", required = true) String orderby) {

        ThemeticProductCri themeticProductCri = new ThemeticProductCri();  //分页
        themeticProductCri.setCurPageNum(curPageNum);
        themeticProductCri.setMaxResult(maxResult);

        themeticProductCri.setProductName(productName);
        themeticProductCri.setIndustry(industry);
        themeticProductCri.setSatellite(satellite);
        themeticProductCri.setSensor(sensor);
        themeticProductCri.setClientName(clientName);
        themeticProductCri.setDelieverName(delieverName);
        themeticProductCri.setOrderby(orderby);

        PageHelper.startPage(themeticProductCri.getCurPageNum(), themeticProductCri.getMaxResult());

        ThemeticProductListResult themeticProductListResult = metadataService.getThemeticProductList(themeticProductCri);
        return ResultUtil.success(themeticProductListResult);



    }

    @RequestMapping(value = "/orthoProductList")  //高级产品列表--正射
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public Result getOrthoProductList(@RequestParam(value = "curPageNum", required = true) int curPageNum,
                                      @RequestParam(value = "maxResult", required = true) int maxResult,
                                      @RequestParam(value = "satellite", required = true) String satellite,
                                      @RequestParam(value = "productName", required = true) String productName,
                                      @RequestParam(value = "sensor", required = true) String sensor,
                                      @RequestParam(value = "clientName", required = true) String clientName,
                                      @RequestParam(value = "delieverName", required = true) String delieverName,
                                      @RequestParam(value = "resolution", required = true) BigDecimal resolution,
                                      @RequestParam(value = "imageBreath", required = true) String imageBreath,
                                      @RequestParam(value = "orderby", required = true) String orderby) {

        OrthoProductCri orthoProductCri = new OrthoProductCri();  //分页
        orthoProductCri.setCurPageNum(curPageNum);
        orthoProductCri.setMaxResult(maxResult);

        orthoProductCri.setProductName(productName);
        orthoProductCri.setSatellite(satellite);
        orthoProductCri.setSensor(sensor);
        orthoProductCri.setClientName(clientName);
        orthoProductCri.setDelieverName(delieverName);
        orthoProductCri.setOrderby(orderby);
        orthoProductCri.setImageBreath(imageBreath);
        orthoProductCri.setResolution(resolution);
        PageHelper.startPage(orthoProductCri.getCurPageNum(), orthoProductCri.getMaxResult());

        OrthoProductListResult orthoProductListResult = metadataService.getOrthoProductList(orthoProductCri);
        return ResultUtil.success(orthoProductListResult);



    }


    @RequestMapping(value = "/subdivisionProductList")  //高级产品列表--分幅
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public Result getSubdivisionProductList(@RequestParam(value = "curPageNum", required = true) int curPageNum,
                                            @RequestParam(value = "maxResult", required = true) int maxResult,
                                            @RequestParam(value = "productName", required = true) String productName,
                                            @RequestParam(value = "clientName", required = true) String clientName,
                                            @RequestParam(value = "delieverName", required = true) String delieverName,
                                            @RequestParam(value = "resolution", required = true) BigDecimal resolution,
                                            @RequestParam(value = "industry", required = true) String industry,
                                            @RequestParam(value = "orderby", required = true) String orderby) {

        SubdivisionProductCri subdivisionProductCri = new SubdivisionProductCri();  //分页
        subdivisionProductCri.setCurPageNum(curPageNum);
        subdivisionProductCri.setMaxResult(maxResult);

        subdivisionProductCri.setProductName(productName);
        subdivisionProductCri.setClientName(clientName);
        subdivisionProductCri.setDelieverName(delieverName);
        subdivisionProductCri.setOrderby(orderby);
        subdivisionProductCri.setIndustry(industry);
        subdivisionProductCri.setResolution(resolution);

        PageHelper.startPage(subdivisionProductCri.getCurPageNum(), subdivisionProductCri.getMaxResult());

        SubdivisionProductListResult subdivisionProductListResult = metadataService.getSubdivisionProductList(subdivisionProductCri);
        return ResultUtil.success(subdivisionProductListResult);



    }

    @RequestMapping(value = "/inlayProductList")  //高级产品列表--镶嵌
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public Result getOrthoProductList(@RequestParam(value = "curPageNum", required = true) int curPageNum,
                                      @RequestParam(value = "maxResult", required = true) int maxResult,
                                      @RequestParam(value = "productName", required = true) String productName,
                                      @RequestParam(value = "clientName", required = true) String clientName,
                                      @RequestParam(value = "delieverName", required = true) String delieverName,
                                      @RequestParam(value = "orderby", required = true) String orderby) {

        InlayProductCri inlayProductCri = new InlayProductCri();  //分页
        inlayProductCri.setCurPageNum(curPageNum);
        inlayProductCri.setMaxResult(maxResult);

        inlayProductCri.setProductName(productName);
        inlayProductCri.setClientName(clientName);
        inlayProductCri.setDelieverName(delieverName);
        inlayProductCri.setOrderby(orderby);

        PageHelper.startPage(inlayProductCri.getCurPageNum(), inlayProductCri.getMaxResult());

        InlayProductListResult inlayProductListResult = metadataService.getInlayProductList(inlayProductCri);
        return ResultUtil.success(inlayProductListResult);



    }



    @RequestMapping(value = "/ThemeticProductListByGeos")  //产品列表
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public Result getThemeticProductListByGeos(@RequestParam(value = "json", required = true) String json )
    {
    System.out.println(json);
    String clientname=new String();
    String productDescription=new String();
    String producer=new String();
    Object imageGeo=new Object();
    JSONObject jsonObject = JSONObject.fromObject(json);
    if(jsonObject.getString("client_name").equals(""))
    {
        clientname=null;
    }
    else
    {
        clientname=jsonObject.getString("client_name");
    }
    if(jsonObject.getString("product_description").equals(""))
    {
        productDescription=null;
    }
    else
    {
        productDescription=jsonObject.getString("product_description");
    }
    if(jsonObject.getString("producer").equals(""))
    {
        //System.out.println("producernull");
        producer=null;
    }
    else
    {
        producer=jsonObject.getString("producer");
    }
    List<Industry> queryIndustryList =new ArrayList<Industry>();
    //System.out.println(jsonObject.getString("industry"));
    if(jsonObject.getString("industry").equals("[]"))
    {

        queryIndustryList =null;
    }
    else
    {
        JSONArray jsonArray=jsonObject.getJSONArray("industry");

        for(int i=0;i<jsonArray.size();i++)
        {
            Industry industrytemp=new Industry();
            industrytemp.setLevel1(jsonArray.getJSONObject(i).getInt("level1"));
            if(jsonArray.getJSONObject(i).getString("level2")=="null")
            {
                industrytemp.setLevel2(10000);
            }
            else
            {
                industrytemp.setLevel2(jsonArray.getJSONObject(i).getInt("level2"));
            }

            System.out.println(industrytemp.getLevel1()+" "+industrytemp.getLevel2());
            queryIndustryList.add(industrytemp);
        }
    }
    if(jsonObject.getString("image_geo").equals(""))
    {
        imageGeo=null;
    }
    else
    {
        imageGeo=jsonObject.getString("image_geo");
    }
    //获取满足clientname和productdescription的ID列表
    List<String>productIdlist=metadataService.getProductIdlist(clientname,productDescription);
    System.out.println("clientname+productDescription"+productIdlist);
    // List<ThemeticProductSimpleInfo> themeticProductSimpleInfoList =metadataService.testgetSimpleProductlist(1);//在数据库内找个GeoJSON
    //获取满足GEO和producer的列表并与上一个列表合并
    List<ThemeticProductSimpleInfo> themeticProductSimpleInfoList1 =metadataService.getThemeticSimpleProductlist( imageGeo,producer);
    System.out.println("imageGeo+producer");
    metadataService.printThemeticSimpleInfoList(themeticProductSimpleInfoList1);
    metadataService.mergeThemeticSimpleInfoListByProductIdlist(themeticProductSimpleInfoList1,productIdlist);
    System.out.println("merge1");
    metadataService.printThemeticSimpleInfoList(themeticProductSimpleInfoList1);
    //获取满足行业信息的ID列表，并与上一个列表合并。
    List<String> productIdlistFromIndustry=new ArrayList<String>();
    productIdlistFromIndustry=metadataService.getProductIdlistByIndustryList(queryIndustryList);
    System.out.println("industrylist:"+productIdlistFromIndustry);
    metadataService.mergeThemeticSimpleInfoListByProductIdlist(themeticProductSimpleInfoList1,productIdlistFromIndustry);
    List<ThemeticProductListByGeosResult> themeticProductListByGeosResultList=new ArrayList<ThemeticProductListByGeosResult>();
    System.out.println("final");
    metadataService.printThemeticSimpleInfoList(themeticProductSimpleInfoList1);
    themeticProductListByGeosResultList=metadataService.packetSingleThemeticProductToThemeticProduct(themeticProductSimpleInfoList1);
    return ResultUtil.success(themeticProductListByGeosResultList);
}



    @RequestMapping(value = "/AdvanceProductListByGeos")  //产品列表
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public Result getOrthoProductListByGeos( @RequestParam(value = "json", required = true) String json) {
        System.out.println(json);
        String clientname=new String();
        String productDescription=new String();
        String producer=new String();
        JSONObject jsonObject = JSONObject.fromObject(json);
        if(jsonObject.getString("client_name").equals(""))
        {
            clientname=null;
        }
        else
        {
            clientname=jsonObject.getString("client_name");
        }
        if(jsonObject.getString("product_description").equals(""))
        {
            productDescription=null;
        }
        else
        {
            productDescription=jsonObject.getString("product_description");
        }
        if(jsonObject.getString("producer").equals(""))
        {
            producer=null;
        }
        else
        {
            producer=jsonObject.getString("producer");
        }
        List<ThemeticProductSimpleInfo> themeticProductSimpleInfoList =metadataService.testgetSimpleProductlist(1);//在数据库内找个GeoJSON themeticProductSimpleInfoList.get(115).getImageGeo()
        List<AdvanceProductSimpleInfo>advanceProductSimpleInfoList=new ArrayList<AdvanceProductSimpleInfo>();
        advanceProductSimpleInfoList=metadataService.getAdvanceProductSimpleInfoList(producer,null,clientname,productDescription);

        return ResultUtil.success(advanceProductSimpleInfoList);
    }

    @RequestMapping(value = "/clientlist")  //产品列表
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public Result getAllClientList( ) {
        System.out.println(iProductArchiveService.getClientNameList(""));
        List<String> clientlist=iProductArchiveService.getClientNameList("");
        clientlist=metadataService.removeRepeat(clientlist);
        System.out.println(clientlist);
        return ResultUtil.success(clientlist);
    }
}


