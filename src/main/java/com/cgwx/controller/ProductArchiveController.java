package com.cgwx.controller;

import com.cgwx.aop.result.Result;
import com.cgwx.aop.result.ResultUtil;
import com.cgwx.common.utils.GeoserverXml;
import com.cgwx.data.dto.ArchivalRecordsItems;
import com.cgwx.data.dto.PdmArchiveRecordsInfoStr;
import com.cgwx.data.dto.UploadFileReturn;
import com.cgwx.data.entity.*;
import com.cgwx.service.IProductArchiveService;
import com.cgwx.service.IProductDownloadService;
import com.cgwx.service.LayerPublishService;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.operation.TransformException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@CrossOrigin
@Controller
public class ProductArchiveController {


    @Autowired
    IProductArchiveService iProductArchiveService;

    @Autowired
    IProductDownloadService iProductDownloadService;

    @Autowired
    LayerPublishService layerPublishService;

    @Autowired
    private AmqpTemplate amqpTemplate;




    @RequestMapping(value = "/test")
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public Result test() throws IOException, FactoryException, TransformException {
//       System.out.println(iProductArchiveService.getNextProductId(4));
//        System.out.println(iProductDownloadService.getEntityFilePath("123"));
//              layerPublishService.publishTif("D:\\Program Files (x86)\\GeoServer 2.13.2\\data_dir\\data\\长春热岛201607.tif","sf","#000000");
        //File file = new File("D:\\Program Files (x86)\\GeoServer 2.13.2\\data_dir\\data\\长春热岛201607.tif");
        //System.out.println(file.toURL());

//       return ResultUtil.success(iProductArchiveService.getSecondaryFileStructure("C:\\Users\\37753\\Desktop\\tmpPic\\哈哈"));
//        iProductArchiveService.copyFolder("C:\\Users\\37753\\Desktop\\产品管理后台\\pdm\\专题产品","C:\\Users\\37753\\Desktop\\产品管理后台\\pdm\\高级产品");
//        JSONObject content = new JSONObject();
//        content.put("path","gou星");
//        amqpTemplate.convertAndSend("publishOrder",content);
        System.out.println("到这了");
        String heeh = "";
        heeh =   iProductArchiveService.xml2jsonString("D:\\样例数据-高级产品管理系统\\分幅产品\\title_K51E007021\\title_K51E007021_cutline.xml");
        return ResultUtil.success(heeh);
    }

    @RequestMapping("/downloadFile")//没用这个函数
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public void downloadFile(HttpServletRequest request, HttpServletResponse response, String productId, String fileName) {

        System.out.println("收到文件下载请求！");
        String filePath = iProductDownloadService.getEntityFilePath(productId);//待重写
        iProductDownloadService.downloadFile(request, response, fileName, filePath);
    }

//    @RequestMapping(method = RequestMethod.GET, value = "/{filename:.+}")
//    @ResponseBody
//    public ResponseEntity<?> getFile(@PathVariable String filename) {
//
//        try {
////            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(ROOT, filename).toString()));
//      System.out.println(filename);
//            File file = new File("C:\\Users\\37753\\Desktop\\临时图片\\师姐老公.jpg");
//            System.out.println(file.toURL().toString());
////            resourceLoader.getClass().getResource(file.toURL().toString());
//            System.out.println("haha");
//
////            return ResponseEntity.ok(classLoader.getResource(file.toURL().toString()));
//
//           return ResponseEntity.ok("gjhj");
//        } catch (Exception e) {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @RequestMapping(value = "/uploadThemeticProduct")//
    @CrossOrigin(methods = RequestMethod.POST)
    @ResponseBody
    public Result uploadThemeticProduct(@RequestParam(value = "file", required = true) MultipartFile file,String archivePersonnel) throws Exception {

        System.out.print("收到专题产品上传请求！");
        System.out.println("guidangrenshi:"+archivePersonnel);
        UploadFileReturn uploadFileReturn = iProductArchiveService.uploadFile(file);
        System.out.println(uploadFileReturn.getFileName());
        System.out.println(uploadFileReturn.getFilePath());
        String path = iProductArchiveService.unZip(uploadFileReturn.getFileName(), uploadFileReturn.getFilePath());
        System.out.println("解压后的路径是：" + path);
        return ResultUtil.success(iProductArchiveService.getSecondaryFileStructureAndWriteCheckTable(path,archivePersonnel,1));

    }

    @RequestMapping(value = "/uploadAdvancedProduct")//
    @CrossOrigin(methods = RequestMethod.POST)
    @ResponseBody
    public Result uploadAdvancedProduct(@RequestParam(value = "file", required = true) MultipartFile file,String archivePersonnel) throws Exception {

        System.out.print("收到高级产品上传请求！");
        System.out.println("归档人Id:"+archivePersonnel);
        UploadFileReturn uploadFileReturn = iProductArchiveService.uploadFile(file);
        String path = iProductArchiveService.unZip(uploadFileReturn.getFileName(), uploadFileReturn.getFilePath());
        System.out.println("解压后的路径是：" + path);
        String  tempId = iProductArchiveService.writeArchiveRecordAndWriteArchiveCheckInfo(path,archivePersonnel,1);
        return ResultUtil.success(tempId);
    }

    @RequestMapping(value = "/commitAdvancedProductInfo")
    @CrossOrigin(methods = RequestMethod.POST)
    @ResponseBody
    public Result commitAdvancedProductInfo(@RequestParam(value = "json", required = true) String json) {

        System.out.println(json);
        //更新产品大表信息
        JSONObject jsonObject = JSONObject.fromObject(json);
        int productType = Integer.parseInt(jsonObject.getString("productType"));
        String productId = iProductArchiveService.getNextProductId(productType);//高级产品
        String productName = iProductArchiveService.getProductName(jsonObject.getString("tempId"));
        PdmProductInfo pdmProductInfo = new PdmProductInfo();
        pdmProductInfo.setProductId(productId);
        pdmProductInfo.setProductName(productName);
        pdmProductInfo.setProductType(productType);
        pdmProductInfo.setProduceArea(jsonObject.getString("produceArea"));
        pdmProductInfo.setProducer(jsonObject.getString("producer"));
        //更新producer
        iProductArchiveService.insertPdmProducerInfo(jsonObject.getString("producer"));
        //更新producer
        pdmProductInfo.setDeliverName(jsonObject.getString("deliverName"));
        pdmProductInfo.setDeliverMethod(jsonObject.getString("deliverMethod"));
        pdmProductInfo.setDataProcessStep(jsonObject.getString("dataProcessStep"));
        String deliverTime = jsonObject.getString("deliverTime").replace("T", " ");
        String produceTime = jsonObject.getString("produceTime").replace("T", " ");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            pdmProductInfo.setDeliverTime(dateFormat.parse(deliverTime));
            pdmProductInfo.setProduceTime(dateFormat.parse(produceTime));
        } catch (ParseException pe) {
            System.out.println(pe.getMessage());
        }
        iProductArchiveService.updateProductInfo(pdmProductInfo);
        //更新产品大表信息

        //正式将数据移入归档区
        String advancedProductPathVar = "";
        String tempPath = iProductArchiveService.getThemeticProductTemporaryPath(jsonObject.getString("tempId"));
        String officialPath = "";
        switch (productType)
        {
            case 2://正射
                advancedProductPathVar = "\\正射产品\\"+productName;
                break;
            case 3://镶嵌
                advancedProductPathVar = "\\镶嵌产品\\"+productName;
                break;
            case 4://分幅
                advancedProductPathVar = "\\分幅产品\\"+productName;
                break;
            default:
                break;
        }
        officialPath = System.getProperty("user.dir") + "\\officialStorage" + advancedProductPathVar;
        String zipFilePath = tempPath+".zip";
        String zipFileTargetPath = officialPath + "\\"+productName+".zip";
        System.out.println("zip文件路径为："+zipFilePath);
        System.out.println("zip文件目的路径为："+zipFileTargetPath);
        File file = new File(officialPath);
        if (!file.exists())
            file.mkdir();
        iProductArchiveService.copyFolder(tempPath, officialPath);
        File zipFile = new File(zipFilePath);
        File zipTargetFile = new File(zipFileTargetPath);
        iProductArchiveService.copyFile(zipFile,zipTargetFile);
        //正式将数据移入归档区

        //更新各自的分表
        switch (productType)
        {
            case 2://正射
                PdmOrthoProductInfo pdmOrthoProductInfo = new PdmOrthoProductInfo();
                pdmOrthoProductInfo.setProductId(productId);
                pdmOrthoProductInfo.setOrthoProductName(productName);
                String orthoBoundaryShpPath = layerPublishService.getBoundaryShpFilePath(officialPath);
                List<JSONObject> jsonObjectOrthoList = layerPublishService.getAdvancedProductShpInfo(orthoBoundaryShpPath);
                String orthoBoundaryGeo = jsonObjectOrthoList.get(0).getString("the_geom");
                System.out.println("边界信息："+jsonObjectOrthoList);
                pdmOrthoProductInfo.setImageGeo(orthoBoundaryGeo);//注意一下，地理信息
                pdmOrthoProductInfo.setOrthoProductDirectory(officialPath);
                pdmOrthoProductInfo.setProducer(jsonObject.getString("producer"));
                //剩下的字段可能需要从xml里面读

                iProductArchiveService.updateOrthoProduct(pdmOrthoProductInfo);
                //开始发布tif
                JSONObject orthoContent = new JSONObject();
                orthoContent.put("path",layerPublishService.getTifFilePath(officialPath));
                orthoContent.put("productId",productId);
                orthoContent.put("productType",productType);
                amqpTemplate.convertAndSend("publishOrder",orthoContent);
                //开始发布tif
                break;
            case 3://镶嵌
                PdmInlayProductInfo pdmInlayProductInfo = new PdmInlayProductInfo();
                pdmInlayProductInfo.setProductId(productId);
                pdmInlayProductInfo.setInlayProductName(productName);
                String inlayBoundaryShpPath = layerPublishService.getBoundaryShpFilePath(officialPath);
                List<JSONObject> jsonObjectInlayList = layerPublishService.getAdvancedProductShpInfo(inlayBoundaryShpPath);
                String inlayBoundaryGeo = jsonObjectInlayList.get(0).getString("the_geom");
                System.out.println("边界信息："+jsonObjectInlayList);
                pdmInlayProductInfo.setImageGeo(inlayBoundaryGeo);
                pdmInlayProductInfo.setInlayProductDirectory(officialPath);
                iProductArchiveService.updateInlayProduct(pdmInlayProductInfo);
                //开始发布tif
                JSONObject inlayContent = new JSONObject();
                inlayContent.put("path",layerPublishService.getTifFilePath(officialPath));
                inlayContent.put("productId",productId);
                inlayContent.put("productType",productType);
                amqpTemplate.convertAndSend("publishOrder",inlayContent);
                //开始发布tif
                break;
            case 4://分幅
                PdmSubdivisionProductInfo pdmSubdivisionProductInfo = new PdmSubdivisionProductInfo();
                pdmSubdivisionProductInfo.setProductId(productId);
                pdmSubdivisionProductInfo.setSubdivisionProductName(productName);
                pdmSubdivisionProductInfo.setSubdivisionProductDirectory(officialPath);
                String subdivisionBoundaryShpPath = layerPublishService.getBoundaryShpFilePath(officialPath);
                List<JSONObject> jsonObjectSubdivisionList = layerPublishService.getAdvancedProductShpInfo(subdivisionBoundaryShpPath);
                String subdivisionBoundaryGeo = jsonObjectSubdivisionList.get(0).getString("the_geom");
                System.out.println("边界信息："+jsonObjectSubdivisionList);
                pdmSubdivisionProductInfo.setImageGeo(subdivisionBoundaryGeo);
                iProductArchiveService.updateSubdivisionProduct(pdmSubdivisionProductInfo);
                //开始发布tif
                JSONObject subdivisionContent = new JSONObject();
                subdivisionContent.put("path",layerPublishService.getTifFilePath(officialPath));
                subdivisionContent.put("productId",productId);
                subdivisionContent.put("productType",productType);
                amqpTemplate.convertAndSend("publishOrder",subdivisionContent);
                //开始发布tif
                break;
            default:
                break;
        }
        //更新各自的分表

        if(productType == 3 || productType == 4 ) //分幅和镶嵌
        {
            //把shp信息存入数据库
            List<JSONObject> jsonObjects = layerPublishService.getAdvancedProductShpInfo(layerPublishService.getShpFilePath(officialPath));
            System.out.println("信息为：" + jsonObjects);
            int count = 0;
            for(JSONObject jsonOB : jsonObjects){
                PdmAdvancedProductShpInfo pdmAdvancedProductShpInfo = new PdmAdvancedProductShpInfo();
                pdmAdvancedProductShpInfo.setProductId(productId);
                pdmAdvancedProductShpInfo.setAcquisitio(jsonOB.getString("Acquisitio"));
                pdmAdvancedProductShpInfo.setChanType(jsonOB.getString("ChanType"));
                pdmAdvancedProductShpInfo.setCloudCover(jsonOB.getString("CloudCover"));
                pdmAdvancedProductShpInfo.setImageFile(jsonOB.getString("ImageFile"));
//                System.out.println("geom为："+jsonOB.getString("the_geom"));
//                String gem = "{\"type\":\"Polygon\",\"coordinates\":[[[125.192856,42.833458],[125.09364,42.83341],[125.09354,42.836964],[125.09578,42.839424],[125.095825,42.840187],[125.09726,42.838524],[125.099945,42.83819],[125.10062,42.837627],[125.10252,42.837627],[125.10297,42.83718],[125.10622,42.83718],[125.106995,42.836845],[125.11013,42.83673],[125.113045,42.836956],[125.11349,42.837402],[125.11942,42.83718],[125.120316,42.838074],[125.12368,42.838074],[125.124794,42.839306],[125.12603,42.839417],[125.12793,42.84177],[125.129944,42.842106],[125.13006,42.842663],[125.13174,42.844456],[125.13308,42.845573],[125.13364,42.845573],[125.13431,42.845127],[125.13801,42.844902],[125.13935,42.84412],[125.14226,42.84412],[125.143715,42.843334],[125.14517,42.843113],[125.14573,42.842106],[125.146736,42.842106],[125.14987,42.840427],[125.15278,42.840202],[125.153786,42.84065],[125.1586,42.840538],[125.158936,42.840874],[125.161736,42.840984],[125.163635,42.840202],[125.16722,42.840202],[125.16744,42.840427],[125.17281,42.840763],[125.17315,42.8411],[125.17685,42.8411],[125.18121,42.840538],[125.182335,42.83953],[125.1858,42.839306],[125.18815,42.837067],[125.18871,42.837067],[125.189384,42.836395],[125.191734,42.83651],[125.19263,42.835613],[125.192856,42.83494],[125.192856,42.833458]]]}";
//                System.out.println("geom是："+gem);
                pdmAdvancedProductShpInfo.setImageGeometry(jsonOB.getString("the_geom"));
                pdmAdvancedProductShpInfo.setImageSourc(jsonOB.getString("ImageSourc"));
                pdmAdvancedProductShpInfo.setIslands(jsonOB.getString("Islands"));
                pdmAdvancedProductShpInfo.setNumChannel(jsonOB.getString("NumChannel"));
                pdmAdvancedProductShpInfo.setSensor(jsonOB.getString("Sensor"));
                pdmAdvancedProductShpInfo.setBlockId(count++);
                iProductArchiveService.updateAdvancedProductShpInfo(pdmAdvancedProductShpInfo);
            }
            //把shp信息存入数据库
        }
        //生成文件发布链接
        iProductDownloadService.generateProductLink(productType,productId,productName);
        //生成文件发布链接

        //操作一波归档记录表
        PdmArchiveRecordsInfo pdmArchiveRecordsInfo = new PdmArchiveRecordsInfo();
        pdmArchiveRecordsInfo.setArchiveResult(1);//0：失败；1：成功
        pdmArchiveRecordsInfo.setProductId(productId);
        pdmArchiveRecordsInfo.setArchiveType(productType);
        iProductArchiveService.updateArchiveRecordsInfo(pdmArchiveRecordsInfo,jsonObject.getString("tempId"),productType);
        //操作一波归档记录表
        return ResultUtil.success(productId);
    }


    @RequestMapping(value = "/commitThemeticProductInfo")
    @CrossOrigin(methods = RequestMethod.POST)
    @ResponseBody
    public Result commitThemeticProductInfo(@RequestParam(value = "json", required = true) String json) {

        System.out.println(json);
        JSONObject jsonObject = JSONObject.fromObject(json);
        String productId = iProductArchiveService.getNextProductId(1);//1:专题
        String productName = iProductArchiveService.getProductName(jsonObject.getString("tempId"));
        PdmProductInfo pdmProductInfo = new PdmProductInfo();
        pdmProductInfo.setProductId(productId);
        pdmProductInfo.setProductName(productName);
        pdmProductInfo.setProductType(1);
        pdmProductInfo.setProductDescription(jsonObject.getString("productDescription"));
        pdmProductInfo.setClientName(jsonObject.getString("clientName"));
        pdmProductInfo.setDeliverName(jsonObject.getString("deliverName"));
        String deliverTime = jsonObject.getString("deliverTime").replace("T", " ");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            pdmProductInfo.setDeliverTime(dateFormat.parse(deliverTime));
        } catch (ParseException pe) {
            System.out.println(pe.getMessage());
        }
        System.out.println(pdmProductInfo.getDeliverTime());
        iProductArchiveService.updateProductInfo(pdmProductInfo);
        System.out.println(jsonObject.getJSONArray("industry"));
        JSONArray industryJsonArray = jsonObject.getJSONArray("industry");
        for (int i = 0; i < industryJsonArray.size(); i++) {
            JSONObject jsonObjectTmp = industryJsonArray.getJSONObject(i);
            PdmThemeticProductDetailIndustryInfo pdmTheProDetIndInfo = new PdmThemeticProductDetailIndustryInfo();
            pdmTheProDetIndInfo.setIndustryLevel1(jsonObjectTmp.getInt("level1"));
            pdmTheProDetIndInfo.setIndustryLevel2(jsonObjectTmp.getInt("level2"));
            pdmTheProDetIndInfo.setProductId(productId);
            iProductArchiveService.updateThemeticProductDetailIndustry(pdmTheProDetIndInfo);
        }
        String tempPath = iProductArchiveService.getThemeticProductTemporaryPath(jsonObject.getString("tempId"));
        String officialPath = System.getProperty("user.dir") + "\\officialStorage\\专题产品\\" + productName;
        String zipFilePath = tempPath+".zip";
        String zipFileTargetPath = officialPath + "\\"+productName+".zip";
        System.out.println("zip文件路径为："+zipFilePath);
        System.out.println("zip文件目的路径为："+zipFileTargetPath);
        File file = new File(officialPath);
        if (!file.exists())
            file.mkdir();
        iProductArchiveService.copyFolder(tempPath, officialPath);
        File zipFile = new File(zipFilePath);
        File zipTargetFile = new File(zipFileTargetPath);
        iProductArchiveService.copyFile(zipFile,zipTargetFile);
        JSONArray singlePeriodProductInfoJsonArray = jsonObject.getJSONArray("singlePeriodProductInfo");
        PdmThemeticProductDetailInfo pdmThemeticProductDetailInfo = new PdmThemeticProductDetailInfo();
        for (int i = 0; i < singlePeriodProductInfoJsonArray.size(); i++) {
            JSONObject jsonObjectTmp = singlePeriodProductInfoJsonArray.getJSONObject(i);
            String imagingTime = jsonObjectTmp.getString("imagingTime").replace("T", " ");
            try {
                pdmThemeticProductDetailInfo.setImagingTime(dateFormat.parse(imagingTime));
            } catch (ParseException pe) {
                System.out.println(pe.getMessage());
            }
            pdmThemeticProductDetailInfo.setProducer(jsonObjectTmp.getString("producer"));
            //更新producer
            iProductArchiveService.insertPdmProducerInfo(jsonObjectTmp.getString("producer"));
            //
            String produceTime = jsonObjectTmp.getString("produceTime").replace("T", " ");
            try {
                pdmThemeticProductDetailInfo.setProduceTime(dateFormat.parse(produceTime));
            } catch (ParseException pe) {
                System.out.println(pe.getMessage());
            }
            pdmThemeticProductDetailInfo.setProductId(productId);
            pdmThemeticProductDetailInfo.setSatellite(jsonObjectTmp.getString("satellite"));
            pdmThemeticProductDetailInfo.setSensor(jsonObjectTmp.getString("sensor"));
            pdmThemeticProductDetailInfo.setSinglePeriodProductDirectory(officialPath + '\\' + jsonObjectTmp.getString("singleName"));//注意以后改了
            pdmThemeticProductDetailInfo.setSinglePeriodProductId(jsonObjectTmp.getString("singleTempId"));
            pdmThemeticProductDetailInfo.setSinglePeriodProductName(jsonObjectTmp.getString("singleName"));
            iProductArchiveService.updateThemeticProductDetail(pdmThemeticProductDetailInfo);
            //开始发布tif
            JSONObject content = new JSONObject();
            content.put("path",layerPublishService.getTifFilePath(officialPath + '\\' + jsonObjectTmp.getString("singleName")));//注意以后改了
            content.put("productId",productId);
            content.put("singleId",jsonObjectTmp.getString("singleTempId"));
            content.put("productType",1);//专题
            amqpTemplate.convertAndSend("publishOrder",content);
            //开始发布tif
        }
        PdmThemeticProductInfo pdmThemeticProductInfo = new PdmThemeticProductInfo();
        pdmThemeticProductInfo.setProductId(productId);
        pdmThemeticProductInfo.setParentDirectory(officialPath);
        pdmThemeticProductInfo.setThemeticProductName(productName);
        iProductArchiveService.updateThemeticProduct(pdmThemeticProductInfo);
        iProductDownloadService.generateProductLink(1,productId,productName);
        //操作一波归档记录表
        PdmArchiveRecordsInfo pdmArchiveRecordsInfo = new PdmArchiveRecordsInfo();
        pdmArchiveRecordsInfo.setArchiveResult(1);
        pdmArchiveRecordsInfo.setProductId(productId);
        iProductArchiveService.updateArchiveRecordsInfo(pdmArchiveRecordsInfo,jsonObject.getString("tempId"),1);
        //操作一波归档记录表
        return ResultUtil.success(productId);
    }

    @RequestMapping(value = "/getArchiveRecords")//time
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public Result getArchiveRecords(@RequestParam(value = "archivePersonnel", required = true) String archivePersonnel,
                                    @RequestParam(value = "curPageNum", required = true) int curPageNum,
                                    @RequestParam(value = "maxResult", required = true) int maxResult,
                                    @RequestParam(value = "condition", required = true) String condition) {

        System.out.println("归档人id为:" + archivePersonnel);
        System.out.println("条件为:" + condition);
        String productName = "";
        int archiveStatus = -1;//状态码
        int archiveType = -1;//状态码
        if (condition != null && !condition.equals("")) {
            productName = condition;
            if ("专题产品".contains(condition))
                archiveType = 1;
            else if ("正射产品".contains(condition))
                archiveType = 2;
            else if ("镶嵌产品".contains(condition))
                archiveType = 3;
            else if ("分幅产品".contains(condition))
                archiveType = 4;
        }
        System.out.println("产品名称：" + productName);
        if (condition.equals("成功"))
            archiveStatus = 1;
        else if (condition.equals("失败"))
            archiveStatus = 0;
        System.out.println("产品类型：" + archiveType);
        System.out.println("产品状态：" + archiveStatus);
        PageInfo<PdmArchiveRecordsInfo> pageInfo = iProductArchiveService.getArchiveRecordList(archivePersonnel, curPageNum, maxResult, productName, archiveType, archiveStatus);
        PageInfo<PdmArchiveRecordsInfoStr> pageInfoForStr = new PageInfo<>();
        pageInfoForStr.setTotal(pageInfo.getTotal());
        pageInfoForStr.setPages(pageInfo.getPages());
        List<PdmArchiveRecordsInfo> archiveRecordList = pageInfo.getList();
        List<PdmArchiveRecordsInfoStr> archiveRecordListWithName = new ArrayList<PdmArchiveRecordsInfoStr>();
        for (PdmArchiveRecordsInfo pdmArchiveRecordsInfo : archiveRecordList) {
            PdmArchiveRecordsInfoStr pdmArchiveRecordsInfoStr = new PdmArchiveRecordsInfoStr();
            String archiverName = pdmArchiveRecordsInfo.getArchivePersonnel();
            pdmArchiveRecordsInfoStr.setArchivePersonnel(iProductArchiveService.getArchivePersonnelName(archiverName));
            pdmArchiveRecordsInfoStr.setArchiveResult(pdmArchiveRecordsInfo.getArchiveResult());
            pdmArchiveRecordsInfoStr.setArchiveType(pdmArchiveRecordsInfo.getArchiveType());
            pdmArchiveRecordsInfoStr.setProductId(pdmArchiveRecordsInfo.getProductId());
            pdmArchiveRecordsInfoStr.setProductName(pdmArchiveRecordsInfo.getProductName());
            SimpleDateFormat formatyyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            pdmArchiveRecordsInfoStr.setArchiveTime(formatyyyy_MM_dd.format(pdmArchiveRecordsInfo.getArchiveTime()));
            archiveRecordListWithName.add(pdmArchiveRecordsInfoStr);
        }
        pageInfoForStr.setList(archiveRecordListWithName);
        return ResultUtil.success(pageInfoForStr);
    }

    @RequestMapping(value = "/getDeliverNameList")
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public Result getDeliverNameList(String deliverName) {
        return ResultUtil.success(iProductArchiveService.getDeliverNameList(deliverName));
    }

    @RequestMapping(value = "/getClientNameList")
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public Result getClientNameList(String clientName) {
        return ResultUtil.success(iProductArchiveService.getClientNameList(clientName));
    }

    @RequestMapping(value = "/getProducerList")
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public Result getProducerList(String producer) {
        return ResultUtil.success(iProductArchiveService.getProducerList(producer));
    }

    @RequestMapping(value = "/readShpTest")//测试
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public Result readShpTest() {

        String path = "D:\\样例数据-高级产品管理系统\\分幅产品\\title_K51E007021_cutline.shp";
        List<JSONObject> jsonObjects = layerPublishService.getAdvancedProductShpInfo(path);
        System.out.println("信息为：" + jsonObjects);

        String path1 = "D:\\样例数据-高级产品管理系统\\镶嵌产品\\MP_吉林_长春_白天";
        System.out.println("路径是："+layerPublishService.getShpFilePath(path1));
        return ResultUtil.success(jsonObjects);

    }

}
