package com.cgwx.controller;


import com.cgwx.aop.result.Result;
import com.cgwx.aop.result.ResultUtil;
import com.cgwx.dao.*;
import com.cgwx.data.dto.*;
import com.cgwx.service.IMetadataService;
import com.cgwx.service.impl.IProductArchiveServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
    @Autowired
    PdmThemeticProductDetailIndustryInfoMapper pdmThemeticProductDetailIndustryInfoMapper;
    @Autowired
    PdmProductStoreLinkInfoMapper pdmProductStoreLinkInfoMapper;
    @Value("${productStoreLinkHead}")
    private String productStoreLinkHead;//拼链接


    @RequestMapping(value = "/themeticProductDetail")  //专题产品详情
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public Result ThemeticProductDetail(@RequestParam(value = "productId", required = true) String productId) {

        List<String> singlePeriodProductIdList=metadataService.selecSinglePeriodThemeticProductList(productId);
       // System.err.println(singlePeriodProductIdList);
        ThemeticProductDetail multiPeriodThemeticProductDetail = metadataService.getThemeticProductDetail(productId,singlePeriodProductIdList);
        return ResultUtil.success(multiPeriodThemeticProductDetail);
    }



    @RequestMapping(value = "/advancedProductDetail")  //高级产品详情
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public Result advancedProductDetail(@RequestParam(value = "productId", required = true) String productId) {

        int productType= metadataService.selectProductTypeByProductId(productId);
        System.out.print(productType);
        String  advancedProductType = metadataService.selectProductTypeDescriptionByProductType(productType);
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



    @RequestMapping(value = "/ThemeticProductListByGeo")  //产品列表
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public Result getThemeticProductListByGeo(@RequestParam(value = "json", required = true) String json ,
                                               @RequestParam(value = "curPageNum", required = true) int curPageNum,
                                               @RequestParam(value = "maxResultNum", required = true) int maxResultNum)
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
    //System.out.println("clientname+productDescription"+productIdlist);
    //获取满足GEO和producer的列表并与上一个列表合并
    List<ThemeticProductSimpleInfo> themeticProductSimpleInfoList1 =metadataService.getThemeticSimpleProductlist( imageGeo,producer);
    //System.out.println("imageGeo+producer");
    //metadataService.printThemeticSimpleInfoList(themeticProductSimpleInfoList1);
    metadataService.mergeThemeticSimpleInfoListByProductIdlist(themeticProductSimpleInfoList1,productIdlist);
    //System.out.println("merge1");
    //metadataService.printThemeticSimpleInfoList(themeticProductSimpleInfoList1);
    //获取满足行业信息的ID列表，并与上一个列表合并。
    List<String> productIdlistFromIndustry=new ArrayList<String>();
    productIdlistFromIndustry=metadataService.getProductIdlistByIndustryList(queryIndustryList);
   // System.out.println("industrylist:"+productIdlistFromIndustry);
    metadataService.mergeThemeticSimpleInfoListByProductIdlist(themeticProductSimpleInfoList1,productIdlistFromIndustry);
    List<ThemeticProductListByGeos> themeticProductListByGeosResultList=new ArrayList<ThemeticProductListByGeos>();
    //System.out.println("final");
    //metadataService.printThemeticSimpleInfoList(themeticProductSimpleInfoList1);
    themeticProductListByGeosResultList=metadataService.packetSingleThemeticProductToThemeticProduct(themeticProductSimpleInfoList1);
    if(themeticProductListByGeosResultList!=null)
    {
        for(int i=0;i<themeticProductListByGeosResultList.size();i++)
        {
            themeticProductListByGeosResultList.get(i).setUrl(productStoreLinkHead+metadataService.selectProductAllfileDownloadurl(themeticProductListByGeosResultList.get(i).getProductId()));
        }
    }
    else {
        return ResultUtil.success(null);
    }
        int pagesize=maxResultNum;
        List<ThemeticProductListByGeos> tmpAdvanceProductSimpleInfoList = new ArrayList<ThemeticProductListByGeos>();
       // System.out.println("CurPageNum:"+curPageNum);
        ThemeticProductListByGeosResult themeticProductListByGeosResult = new ThemeticProductListByGeosResult();
        for(int i=(curPageNum-1)*pagesize;i<(curPageNum)*pagesize;i++)
        {
            tmpAdvanceProductSimpleInfoList.add(themeticProductListByGeosResultList.get(i));
            if(i==themeticProductListByGeosResultList.size()-1)
            {
                break;
            }
        }
        themeticProductListByGeosResult.setTotalItems(themeticProductListByGeosResultList.size());
        themeticProductListByGeosResult.setTotalPageNum((int)Math.ceil((double)themeticProductListByGeosResultList.size()/pagesize));
        themeticProductListByGeosResult.setProductQueryList(tmpAdvanceProductSimpleInfoList);
    return ResultUtil.success(themeticProductListByGeosResult);
}



    @RequestMapping(value = "/ThemeticProductListByGeos")  //产品列表
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public Result getThemeticProductListByGeos(@RequestParam(value = "json", required = true) String json ,
                                               @RequestParam(value = "curPageNum", required = true) int curPageNum,
                                               @RequestParam(value = "maxResultNum", required = true) int maxResultNum)
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
        List<ThemeticProductListByGeos> themeticProductListByGeosResultList=new ArrayList<ThemeticProductListByGeos>();
        //System.out.println("final");
        //metadataService.printThemeticSimpleInfoList(themeticProductSimpleInfoList1);
        PageHelper.startPage(curPageNum,maxResultNum);
        themeticProductListByGeosResultList=metadataService.getThemeticProductListByConditions(clientname,productDescription,imageGeo,producer,queryIndustryList);
        if(themeticProductListByGeosResultList!=null)
        {
            for(int i=0;i<themeticProductListByGeosResultList.size();i++)
            {
                themeticProductListByGeosResultList.get(i).setUrl(productStoreLinkHead+metadataService.selectProductAllfileDownloadurl(themeticProductListByGeosResultList.get(i).getProductId()));
                themeticProductListByGeosResultList.get(i).setThemeticProductSimpleInfoList(metadataService.selectSimpleinfoById(themeticProductListByGeosResultList.get(i).getProductId()));
                themeticProductListByGeosResultList.get(i).setIndustryList(metadataService.selectIndustryByProductid(themeticProductListByGeosResultList.get(i).getProductId()));
            }
        }
        else {
            return ResultUtil.success(null);
        }
        PageInfo<ThemeticProductListByGeos> pageInfo = new PageInfo<>(themeticProductListByGeosResultList);
        System.out.println("z总条目数：" + pageInfo.getTotal());
        System.out.println("z总页数：" + pageInfo.getPages());
        ThemeticProductListByGeosResult themeticProductListByGeosResult = new ThemeticProductListByGeosResult();
        themeticProductListByGeosResult.setTotalItems(pageInfo.getTotal());
        themeticProductListByGeosResult.setTotalPageNum(pageInfo.getPages());
        themeticProductListByGeosResult.setProductQueryList(themeticProductListByGeosResultList);
        return ResultUtil.success(themeticProductListByGeosResult);
    }




//    @RequestMapping(value = "/AdvanceProductListByGeos")  //产品列表
//    @CrossOrigin(methods = RequestMethod.GET)
//    @ResponseBody
//    public Result getAdvanceProductListByGeos(@RequestParam(value = "json", required = true) String json,
//                                              @RequestParam(value = "curPageNum", required = true) int curPageNum) {
//        System.out.println(json+"curPageNum:"+curPageNum);
//        JSONObject jsonObject = JSONObject.fromObject(json);
//        AdvanceProductCri advanceProductCri=new AdvanceProductCri();
//        advanceProductCri.setOrtho(jsonObject.getBoolean("orthoProduct"));
//        advanceProductCri.setInlay(jsonObject.getBoolean("inlayProduct"));
//        advanceProductCri.setSubdivision(jsonObject.getBoolean("subdivisionProduct"));
//        advanceProductCri.setCurPageNum(curPageNum);
//        if(jsonObject.getString("advProductName").equals(""))
//        {
//            advanceProductCri.setProductName(null);
//        }
//        else
//        {
//            advanceProductCri.setProductName(jsonObject.getString("advProductName"));
//        }
//        if(jsonObject.getString("advProduceAddress").equals(""))
//        {
//            advanceProductCri.setProduceArea(null);
//        }
//        else
//        {
//            advanceProductCri.setProduceArea(jsonObject.getString("advProduceAddress"));
//        }
//        if(jsonObject.getString("advProducePerson").equals(""))
//        {
//            advanceProductCri.setProducer(null);
//        }
//        else
//        {
//            advanceProductCri.setProducer(jsonObject.getString("advProducePerson"));
//        }
//        if(jsonObject.getString("advDataDelivery").equals(""))
//        {
//            advanceProductCri.setDeliverMethod(null);
//        }
//        else
//        {
//            advanceProductCri.setDeliverMethod(jsonObject.getString("advDataDelivery"));
//        }
//        if(jsonObject.getString("advDataDeliveryObject").equals(""))
//        {
//            advanceProductCri.setDeliverName(null);
//        }
//        else
//        {
//            advanceProductCri.setDeliverName(jsonObject.getString("advDataDeliveryObject"));
//        }
//        if(jsonObject.getString("image_geo").equals(""))
//        {
//            advanceProductCri.setImage_geo(null);
//        }
//        else
//        {
//            advanceProductCri.setImage_geo(jsonObject.getString("image_geo"));
//        }
//        if(jsonObject.getString("advProduceStartTime").equals(""))
//        {
//            advanceProductCri.setProduceStartTime(null);
//        }
//        else
//        {
//            Timestamp ts = new Timestamp(System.currentTimeMillis());
//            ts = Timestamp.valueOf(jsonObject.getString("advProduceStartTime"));
//            advanceProductCri.setProduceStartTime(ts);
//        }
//        if(jsonObject.getString("advProduceEndTime").equals(""))
//        {
//            advanceProductCri.setProduceEndTime(null);
//        }
//        else
//        {
//            Timestamp ts = new Timestamp(System.currentTimeMillis());
//            ts = Timestamp.valueOf(jsonObject.getString("advProduceEndTime"));
//            advanceProductCri.setProduceEndTime(ts);
//        }
//        if(jsonObject.getString("advDeliveryStartTime").equals(""))
//        {
//            advanceProductCri.setDeliverStartTime(null);
//        }
//        else
//        {
//            Timestamp ts = new Timestamp(System.currentTimeMillis());
//            ts = Timestamp.valueOf(jsonObject.getString("advDeliveryStartTime"));
//            advanceProductCri.setDeliverStartTime(ts);
//        }
//        if(jsonObject.getString("advDeliveryEndTime").equals(""))
//        {
//            advanceProductCri.setDeliverEndTime(null);
//        }
//        else
//        {
//            Timestamp ts = new Timestamp(System.currentTimeMillis());
//            ts = Timestamp.valueOf(jsonObject.getString("advDeliveryEndTime"));
//            advanceProductCri.setDeliverEndTime(ts);
//        }
//        List<AdvanceProductSimpleInfo> advanceProductSimpleInfoList=metadataService.getAdvanceProductSimpleInfoList(advanceProductCri);
//
//        //System.out.println(advanceProductSimpleInfoList);
//        if(advanceProductSimpleInfoList.size()==0)
//        {
//            return ResultUtil.success(null);
//        }
//        int pagesize=5;
//        List<AdvanceProductSimpleInfo> tmpAdvanceProductSimpleInfoList = new ArrayList<AdvanceProductSimpleInfo>();
//        int CurPageNum=advanceProductCri.getCurPageNum();
//        //System.out.println("CurPageNum:"+CurPageNum);
//        AdvanceProductSimpleInfoResult advanceProductSimpleInfoResult = new AdvanceProductSimpleInfoResult();
//        for(int i=(CurPageNum-1)*pagesize;i<(CurPageNum)*pagesize;i++)
//        {
//            tmpAdvanceProductSimpleInfoList.add(advanceProductSimpleInfoList.get(i));
//            if(i==advanceProductSimpleInfoList.size()-1)
//            {
//                break;
//            }
//        }
//       // System.out.println(tmpAdvanceProductSimpleInfoList);
//        advanceProductSimpleInfoResult.setTotalItems(advanceProductSimpleInfoList.size());
//        advanceProductSimpleInfoResult.setTotalPageNum((int)Math.ceil((double)advanceProductSimpleInfoList.size()/pagesize));
//        advanceProductSimpleInfoResult.setProductQueryList(tmpAdvanceProductSimpleInfoList);
//        return ResultUtil.success(advanceProductSimpleInfoResult);
//    }

    @RequestMapping(value = "/AdvanceProductListByGeos")  //产品列表
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public Result getAdvanceProductListByGeos(@RequestParam(value = "json", required = true) String json,
                                              @RequestParam(value = "curPageNum", required = true) int curPageNum,
                                              @RequestParam(value = "maxResultNum", required = true) int maxResultNum) {
        System.out.println(json+"curPageNum:"+curPageNum);
        JSONObject jsonObject = JSONObject.fromObject(json);
        AdvanceProductCri advanceProductCri=new AdvanceProductCri();
        advanceProductCri.setOrtho(jsonObject.getBoolean("orthoProduct"));
        advanceProductCri.setInlay(jsonObject.getBoolean("inlayProduct"));
        advanceProductCri.setSubdivision(jsonObject.getBoolean("subdivisionProduct"));
        advanceProductCri.setCurPageNum(curPageNum);
        advanceProductCri.setMaxResultNum(maxResultNum);
        boolean flag=false;
        if(advanceProductCri.isSubdivision()||advanceProductCri.isInlay()||advanceProductCri.isOrtho())
        {
            flag=true;
        }
        if(flag==false)
        {
            return ResultUtil.success(null);
        }
        if(jsonObject.getString("advProductName").equals(""))
        {
            advanceProductCri.setProductName(null);
        }
        else
        {
            advanceProductCri.setProductName(jsonObject.getString("advProductName"));
        }
        if(jsonObject.getString("advProduceAddress").equals(""))
        {
            advanceProductCri.setProduceArea(null);
        }
        else
        {
            advanceProductCri.setProduceArea(jsonObject.getString("advProduceAddress"));
        }
        if(jsonObject.getString("advProducePerson").equals(""))
        {
            advanceProductCri.setProducer(null);
        }
        else
        {
            advanceProductCri.setProducer(jsonObject.getString("advProducePerson"));
        }
        if(jsonObject.getString("advDataDelivery").equals(""))
        {
            advanceProductCri.setDeliverMethod(null);
        }
        else
        {
            advanceProductCri.setDeliverMethod(jsonObject.getString("advDataDelivery"));
        }
        if(jsonObject.getString("advDataDeliveryObject").equals(""))
        {
            advanceProductCri.setDeliverName(null);
        }
        else
        {
            advanceProductCri.setDeliverName(jsonObject.getString("advDataDeliveryObject"));
        }
        if(jsonObject.getString("image_geo").equals(""))
        {
            advanceProductCri.setImage_geo(null);
        }
        else
        {
            advanceProductCri.setImage_geo(jsonObject.getString("image_geo"));
        }
        if(jsonObject.getString("advProduceStartTime").equals(""))
        {
            advanceProductCri.setProduceStartTime(null);
        }
        else
        {
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            ts = Timestamp.valueOf(jsonObject.getString("advProduceStartTime"));
            advanceProductCri.setProduceStartTime(ts);
        }
        if(jsonObject.getString("advProduceEndTime").equals(""))
        {
            advanceProductCri.setProduceEndTime(null);
        }
        else
        {
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            ts = Timestamp.valueOf(jsonObject.getString("advProduceEndTime"));
            advanceProductCri.setProduceEndTime(ts);
        }
        if(jsonObject.getString("advDeliveryStartTime").equals(""))
        {
            advanceProductCri.setDeliverStartTime(null);
        }
        else
        {
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            ts = Timestamp.valueOf(jsonObject.getString("advDeliveryStartTime"));
            advanceProductCri.setDeliverStartTime(ts);
        }
        if(jsonObject.getString("advDeliveryEndTime").equals(""))
        {
            advanceProductCri.setDeliverEndTime(null);
        }
        else
        {
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            ts = Timestamp.valueOf(jsonObject.getString("advDeliveryEndTime"));
            advanceProductCri.setDeliverEndTime(ts);
        }
        AdvanceProductSimpleInfoResult advanceProductSimpleInfoResult=metadataService.getAdvanceProductSimpleInfoListByConditions(advanceProductCri);

        //System.out.println(advanceProductSimpleInfoList);

        return ResultUtil.success(advanceProductSimpleInfoResult);
    }


    @RequestMapping(value = "/clientlist")  //产品列表
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public Result getAllClientList( ) {
       // System.out.println(iProductArchiveService.getClientNameList(""));
        List<String> clientlist=iProductArchiveService.getClientNameList("");
        clientlist=metadataService.removeRepeat(clientlist);
        for(int i=0;i<clientlist.size();i++)
        {
            if(clientlist.get(i).equals(""))
            {
                clientlist.remove(i);
            }
        }
       // System.out.println(clientlist);
        return ResultUtil.success(clientlist);
    }

    @RequestMapping(value = "/producerlist")  //产品列表
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public Result getAllProducerList( ) {
        //System.out.println(iProductArchiveService.getProducerList(""));
        List<String> producerList=iProductArchiveService.getProducerList("");
        producerList=metadataService.removeRepeat(producerList);
        for(int i=0;i<producerList.size();i++)
        {
            if(producerList.get(i).equals(""))
            {
                producerList.remove(i);
            }
        }
        //System.out.println(producerList);
        return ResultUtil.success(producerList);
    }
    @RequestMapping(value = "/delivernamelist")  //产品列表
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public Result getAllDeliverNameList( ) {
        //System.out.println(iProductArchiveService.getProducerList(""));
        List<String> deliverNameList=iProductArchiveService.getDeliverNameList("");
        deliverNameList=metadataService.removeRepeat(deliverNameList);
        for(int i=0;i<deliverNameList.size();i++)
        {
            if(deliverNameList.get(i).equals(""))
            {
                deliverNameList.remove(i);
            }
        }
        //System.out.println(producerList);
        return ResultUtil.success(deliverNameList);
    }
    @RequestMapping(value = "/delivermethodlist")  //产品列表
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public Result getAllDeliverMethodList( ) {
        //System.out.println(iProductArchiveService.getProducerList(""));
        List<String> deliverMethodList =metadataService.getDeliverMethodList();
        deliverMethodList =metadataService.removeRepeat(deliverMethodList);
        for(int i = 0; i< deliverMethodList.size(); i++)
        {
            if(deliverMethodList.get(i).equals(""))
            {
                deliverMethodList.remove(i);
            }
        }
        //System.out.println(producerList);
        return ResultUtil.success(deliverMethodList);
    }

    @RequestMapping(value = "/producearealist")  //产品列表
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public Result getProduceAreaList( ) {
        //System.out.println(iProductArchiveService.getProducerList(""));
        List<String> produceAreaList =metadataService.getProduceAreaList();
        produceAreaList =metadataService.removeRepeat(produceAreaList);
        for(int i = 0; i< produceAreaList.size(); i++)
        {
            if(produceAreaList.get(i).equals(""))
            {
                produceAreaList.remove(i);
            }
        }
        //System.out.println(producerList);
        return ResultUtil.success(produceAreaList);
    }

    @RequestMapping(value = "/allFileDownloadUrl")  //产品列表
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public Result getAllFielDownloadurl( @RequestParam(value = "productId", required = true) String productId) {
        //System.out.println(iProductArchiveService.getProducerList(""));
       String url=productStoreLinkHead+metadataService.selectProductAllfileDownloadurl(productId);
       return ResultUtil.success(url);
    }
}


