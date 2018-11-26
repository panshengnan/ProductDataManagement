package com.cgwx.controller;

import com.cgwx.aop.result.Result;
import com.cgwx.aop.result.ResultUtil;
import com.cgwx.data.dto.ArchivalRecordsItems;
import com.cgwx.data.dto.UploadFileReturn;
import com.cgwx.data.entity.*;
import com.cgwx.service.IProductArchiveService;
import com.cgwx.service.IProductDownloadService;
import com.cgwx.service.LayerPublishService;
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
        //      layerPublishService.publishTif("D:\\Program Files (x86)\\GeoServer 2.13.2\\data_dir\\data\\长春热岛201607.tif","re","red");
        //File file = new File("D:\\Program Files (x86)\\GeoServer 2.13.2\\data_dir\\data\\长春热岛201607.tif");
        //System.out.println(file.toURL());

//       return ResultUtil.success(iProductArchiveService.getSecondaryFileStructure("C:\\Users\\37753\\Desktop\\tmpPic\\哈哈"));
//        iProductArchiveService.copyFolder("C:\\Users\\37753\\Desktop\\产品管理后台\\pdm\\专题产品","C:\\Users\\37753\\Desktop\\产品管理后台\\pdm\\高级产品");
        String content="打死啊是";
        amqpTemplate.convertAndSend("publishOrder",content);

        return ResultUtil.success(content);
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

    @RequestMapping(value = "/commitThemeticProductInfo")
    @CrossOrigin(methods = RequestMethod.POST)
    @ResponseBody
    public Result commitThemeticProductInfo(@RequestParam(value = "json", required = true) String json) {

        System.out.println(json);
        JSONObject jsonObject = JSONObject.fromObject(json);
        String productId = iProductArchiveService.getNextProductId(1);//1:专题
        String productName = iProductArchiveService.getThemeticProductName(jsonObject.getString("tempId"));
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
        iProductArchiveService.updateProductInfoForTheme(pdmProductInfo);
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
            pdmThemeticProductDetailInfo.setSinglePeriodProductDirectory(officialPath + '\\' + jsonObjectTmp.getString("singleName"));
            pdmThemeticProductDetailInfo.setSinglePeriodProductId(jsonObjectTmp.getString("singleTempId"));
            pdmThemeticProductDetailInfo.setSinglePeriodProductName(jsonObjectTmp.getString("singleName"));
            iProductArchiveService.updateThemeticProductDetail(pdmThemeticProductDetailInfo);
        }
        PdmThemeticProductInfo pdmThemeticProductInfo = new PdmThemeticProductInfo();
        pdmThemeticProductInfo.setProductId(productId);
        pdmThemeticProductInfo.setParentDirectory(officialPath);
        pdmThemeticProductInfo.setThemeticProductName(productName);
        iProductArchiveService.updateThemeticProduct(pdmThemeticProductInfo);
        iProductDownloadService.generateProductLink(1,productId,productName);
        //
        //操作一波归档记录表
        PdmArchiveRecordsInfo pdmArchiveRecordsInfo = new PdmArchiveRecordsInfo();
        pdmArchiveRecordsInfo.setArchiveResult(1);
        pdmArchiveRecordsInfo.setProductId(productId);
        iProductArchiveService.updateArchiveRecordsInfo(pdmArchiveRecordsInfo,jsonObject.getString("tempId"));
        //操作一波归档记录表
        return ResultUtil.success("success");
    }

    @RequestMapping(value = "/getArchiveRecords")
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public Result getArchiveRecords(String archivePersonnel) {

        System.out.println("归档id为:"+archivePersonnel);

        List<PdmArchiveRecordsInfo> archiveRecordList = iProductArchiveService.getArchiveRecordList(archivePersonnel);
        List<PdmArchiveRecordsInfo> archiveRecordListWithName = new ArrayList<PdmArchiveRecordsInfo>();

        for(PdmArchiveRecordsInfo pdmArchiveRecordsInfo : archiveRecordList)
        {
            String archiverName = pdmArchiveRecordsInfo.getArchivePersonnel();
            pdmArchiveRecordsInfo.setArchivePersonnel(iProductArchiveService.getArchivePersonnelName(archiverName));
            archiveRecordListWithName.add(pdmArchiveRecordsInfo);  //注意一下有没有同一地址问题
        }
        return ResultUtil.success(archiveRecordListWithName);
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

    @RequestMapping(value = "/getProducerList")/**/
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public Result getProducerList(String producer) {
        return ResultUtil.success(iProductArchiveService.getProducerList(producer));
    }

    @RequestMapping(value = "/readShpTest")/**/
    @CrossOrigin(methods = RequestMethod.GET)
    @ResponseBody
    public Result readShpTest() {

        String path1 = "D:\\样例数据-高级产品管理系统\\分幅产品\\title_K51E007021_cutline.shp";
        //读取shp
        SimpleFeatureCollection colls1 = layerPublishService.readShp(path1);
        //拿到所有features
        SimpleFeatureIterator iters = colls1.features();
        //遍历打印
        List<JSONObject> jSONObjects = new ArrayList<JSONObject>();
        while (iters.hasNext()) {
            SimpleFeature sf = iters.next();
            System.out.println(sf.getID() + " , " + sf.getAttributes());
//            System.out.println(sf.getAttribute("ImageFile"));
//            System.out.println(sf.getFeatureType());
//            System.out.println(sf.getProperties());
            System.out.println("名字是"+sf.getName());
            String[] strList = (sf.getProperties()).toString().split("SimpleFeatureImpl.Attribute");
//            System.out.println("分割结果：");
            JSONObject jsonObject = new JSONObject();
            for (int i = 0; i < strList.length; i++) {
                String tmp = strList[i];
//                System.out.println("原串是："+tmp);
                String key="";
                String value="";
                if(i>0&&i<strList.length-1)
                {
                    key = tmp.substring(2, tmp.indexOf('<'));
                    System.out.println("key是：" + key);
                    String comma = ",";

                    if(comma.equals((tmp.charAt(tmp.length()-1))))
                    value="";
                    else
                    value = tmp.substring(tmp.lastIndexOf('=') + 1,tmp.lastIndexOf(','));
                    System.out.println("value是：" + value);
                }
                if(i==strList.length-1)
                {
                    {
                        key = tmp.substring(2, tmp.indexOf('<'));
                        System.out.println("key是：" + key);
                        value = tmp.substring(tmp.lastIndexOf('=') + 1,tmp.lastIndexOf(']'));
                        System.out.println("value是：" + value);
                    }

                }
                if(i!=0)
                jsonObject.put(key,value);
            }
            jSONObjects.add(jsonObject);
            System.out.println("json为："+jsonObject);
        }
            return ResultUtil.success(jSONObjects);

    }

}
