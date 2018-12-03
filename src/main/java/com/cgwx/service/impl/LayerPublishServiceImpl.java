package com.cgwx.service.impl;


import com.cgwx.aop.result.Result;
import com.cgwx.aop.result.ResultUtil;
import com.cgwx.common.constants.LayerInfo;
import com.cgwx.common.utils.GeoserverXml;
import com.cgwx.common.utils.PublishLayer;
import com.cgwx.dao.PdmProductLayerInfoMapper;
import com.cgwx.dao.PdmThemeticProductDetailInfoMapper;
import com.cgwx.data.dto.PolygonObject;
import com.cgwx.data.entity.PdmProductLayerInfo;
import com.cgwx.service.LayerPublishService;
import com.google.gson.Gson;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.WKTReader;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;

import org.geotools.data.shapefile.ShapefileDataStore;////
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.gce.geotiff.GeoTiffFormat;
import org.geotools.gce.geotiff.GeoTiffReader;
import org.geotools.geojson.geom.GeometryJSON;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.geometry.Envelope;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.opengis.filter.Filter;

import static net.sf.json.JSONObject.*;


@Service
public class LayerPublishServiceImpl implements LayerPublishService
{
    @Autowired
    LayerInfo layerInfo;

    @Autowired
    PdmProductLayerInfoMapper pdmProductLayerInfoMapper;

    @Autowired
    PdmThemeticProductDetailInfoMapper pdmThemeticProductDetailInfoMapper;

    @Override
    public Result publishTifToGeoserver(JSONObject jsonObject) throws ParserConfigurationException, IOException {
//        String fileName = jsonObject.get("filename").toString();
        JSONObject resultObject=new JSONObject();
        HttpClient httpClient=new HttpClient();
        UsernamePasswordCredentials creds = new UsernamePasswordCredentials(layerInfo.getGeoserverUsername() ,layerInfo.getGeoserverPassword());
        System.out.println(layerInfo.getGeoserverUsername());
        httpClient.getState().setCredentials(AuthScope.ANY, creds);
        httpClient.getParams().setContentCharset("UTF-8");
        String url=layerInfo.getGeoserverPath()+"/rest/workspaces/"+jsonObject.getString("namespace")+"/coveragestores";
        String data= GeoserverXml.GetCoveragestoreXml(jsonObject);
        PostMethod postMethod=new PostMethod(url);
        RequestEntity requestEntity=new StringRequestEntity(data,"application/xml","UTF-8");
        postMethod.setRequestEntity(requestEntity);
        int result=httpClient.executeMethod(postMethod);
        if(result!=201)
        {

            if(result==500)
            {
                System.out.println("存在同名图层");
                return ResultUtil.error(511,"存在同名图层");

            }
            else
            {
                System.out.println(postMethod.getResponseBodyAsString());
                System.out.println(result);
                System.out.println("发布存储失败");
                return  ResultUtil.error(511,"发布存储失败");}
        }
        data= GeoserverXml.getCoverageXml(jsonObject);
        url=layerInfo.getGeoserverPath()+"/rest/workspaces/"+jsonObject.getString("namespace")+"/coveragestores/"+ URLEncoder.encode(jsonObject.getString("storename"),"utf-8")+"/coverages";
        postMethod=new PostMethod(url);
        postMethod.setRequestHeader("Content-type","text/xml");
        requestEntity=new StringRequestEntity(data,"application/xml","UTF-8");
        postMethod.setRequestEntity(requestEntity);
        result = httpClient.executeMethod(postMethod);
        if(result!=201)
        {

            url=layerInfo.getGeoserverPath()+"/rest/workspaces/"+jsonObject.getString("namespace")+"/coveragestores/"+URLEncoder.encode(jsonObject.getString("storename"),"utf-8");
            DeleteMethod deleteMethod=new DeleteMethod(url);
            httpClient.executeMethod(deleteMethod);
            return ResultUtil.error(512,postMethod.getResponseBodyAsString());
        }

      return ResultUtil.success();
    }

    @Override
    public JSONObject publishTif(String filePath, String nameSpace, String borderColor) throws IOException, FactoryException, TransformException {
        JSONObject jsonObject = new JSONObject();
        File file = new File(filePath);
        String name = file.getName().split("\\.")[0];
        jsonObject.put("filename", name);
        if(name.length()>20)name=name.substring(0,20);
        GeoTiffReader reader;
        GeoTiffFormat format = new GeoTiffFormat();
        File file2 = new File(filePath);
        reader = format.getReader(file2.toURL());
        if (reader == null) {
//            return ResultUtil.error(510, "Tif文件无法打开").toString();//注意一下
        }
        GridCoverage2D coverage = reader.read(null);
        String crs = coverage.getCoordinateReferenceSystem().toString();
        if (crs == null) {
//            return ResultUtil.error(510, "文件缺少坐标系").toString();//注意一下
        }
        Envelope envelope = coverage.getEnvelope();
        AffineTransform art = (AffineTransform) coverage.getGridGeometry().getGridToCRS();
        jsonObject.put("namespace", nameSpace);
        jsonObject.put("storename", name);
        jsonObject.put("layername", name);
        jsonObject.put("srs", "EPSG" + ":" + PublishLayer.GetCrsEPSG(crs));
        jsonObject.put("nativeBoundingBox", "[" + envelope.getMinimum(0) + "," + envelope.getMaximum(0) + "," + envelope.getMinimum(1) + "," + envelope.getMaximum(1) + "]");
        MathTransform transform = CRS.findMathTransform(coverage.getCoordinateReferenceSystem(), DefaultGeographicCRS.WGS84);
        com.vividsolutions.jts.geom.Envelope envelope1 = new com.vividsolutions.jts.geom.Envelope(envelope.getMinimum(0), envelope.getMaximum(0), envelope.getMinimum(1), envelope.getMaximum(1));
        if (!PublishLayer.GetCrsEPSG(crs).equals("4326")) envelope1 = JTS.transform(envelope1, transform);
        jsonObject.put("latLonBoundingBox", "[" + envelope1.getMinX() + "," + envelope1.getMaxX() + "," + envelope1.getMinY() + "," + envelope1.getMaxY() + "]");
//        System.out.println("真的范围是："+jsonObject.get("latLonBoundingBox"));
        com.vividsolutions.jts.geom.Polygon polygon = JTS.toGeometry(envelope1);
        System.out.println("Polygon是："+polygon);
// ！！！！！！！！！！！！！！！！！！！！！！！！！！       GeometryJSON g = new GeometryJSON();

        jsonObject.put("nativeCRS", crs);
        jsonObject.put("filepath", filePath);
        jsonObject.put("rasterXSize", coverage.getRenderedImage().getWidth());
        jsonObject.put("rasterYSize", coverage.getRenderedImage().getHeight());
        jsonObject.put("scaleX", art.getScaleX());
        jsonObject.put("scaleY", art.getScaleY());
        jsonObject.put("shearX", art.getShearX());
        jsonObject.put("shearY", art.getShearY());

        jsonObject.put("translateX", art.getTranslateX());
        jsonObject.put("translateY", art.getTranslateY());
        jsonObject.put("borderColor", borderColor);
        jsonObject.put("data_type", "image");
        jsonObject.put("geoserverPath", layerInfo.getGeoserverPath());
        //注意一下
        JSONObject jsonObjectReturn = new JSONObject();
        String geoJsonReturn = transformPOLYGONToPolygon(getGj(polygon.toString()));
        System.out.println("Polygon2是："+geoJsonReturn);
        jsonObjectReturn.put("fileName",name);
        jsonObjectReturn.put("geoJson",geoJsonReturn);
        //注意一下
        try {
            Result result= publishTifToGeoserver(jsonObject);
            if(result.getStatus()==200)
            {
                JSONObject param=new JSONObject();

                param.put("layerName",nameSpace+":"+name);
                if(coverage.getCoordinateReferenceSystem().toString().contains("UTM"))param.put("projection","UTM");
                else param.put("projection","WGS-84");
                return jsonObjectReturn;
//                return   ResultUtil.success(param.toString()).toString();
            }
//            return result.toString();
            return jsonObjectReturn;

        } catch (ParserConfigurationException e) {
            System.out.println("发布失败");
            return  jsonObjectReturn;
//            return ResultUtil.error(511,"发布失败").toString();
        }

    }

    @Override
    public SimpleFeatureCollection readShp(String path ){
        return readShp(path, null);

    }

     SimpleFeatureCollection  readShp(String path ,Filter filter){
        SimpleFeatureSource featureSource = readStoreByShp(path);
        if(featureSource == null) return null;
        try {
            return filter != null ? featureSource.getFeatures(filter) : featureSource.getFeatures() ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null ;
    }

     SimpleFeatureSource readStoreByShp(String path ){
        File file = new File(path);
        FileDataStore store;
        SimpleFeatureSource featureSource = null;
        try {
            store = FileDataStoreFinder.getDataStore(file);
            ((ShapefileDataStore) store).setStringCharset(Charset.forName("GBK"));
            System.out.println(Charset.forName("UTF-8"));
            featureSource = store.getFeatureSource();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return featureSource ;
    }


    @Override //存在兼容问题
    public String getMULTIPOLYGONWktToJson(String wkt, int wkid) {
        PolygonObject polygonObject = new PolygonObject();
        List<List<Double[]>> lists = new ArrayList<List<Double[]>>();

        String ToTailWkt = wkt.substring(0, wkt.length() - 1);
        String[] strHead = ToTailWkt.split("\\(", 2);
        ToTailWkt = strHead[1].substring(0, strHead[1].length() - 1);
        String[] strHeads = ToTailWkt.split("\\(", 2);
        String[] strList = strHeads[1].split("\\), \\(");
        if (strList.length == 1) {
            for (int i = 0; i < strList.length; i++) {
                String item = strList[i].trim();
                item = item.substring(1, item.length() - 1);
                String[] items = item.split(",");
                List<Double[]> list = new ArrayList<Double[]>();
                for (int j = 0; j < items.length; j++) {
                    String jItem = items[j].trim();
                    String[] jItems = jItem.split(" ");
                    Double[] listResult = new Double[] {
                            Double.parseDouble(jItems[0]),
                            Double.parseDouble(jItems[1]) };
                    list.add(listResult);
                }
                lists.add(list);
            }
        } else {
            for (int i = 0; i < strList.length; i++) {
                String item = strList[i].trim();
                item = item.substring(1, item.length() - 1);
                String[] items = item.split(",");
                List<Double[]> list = new ArrayList<Double[]>();
                for (int j = 1; j < items.length; j++) {
                    String jItem = items[j].trim();
                    String[] jItems = jItem.split(" ");
                    Double[] listResult = new Double[] {
                            Double.parseDouble(jItems[0]),
                            Double.parseDouble(jItems[1]) };
                    list.add(listResult);
                }
                lists.add(list);
            }
        }
        HashMap<String, Integer> spatialReference = new HashMap<String, Integer>();
        spatialReference.put("wkid", wkid);
        polygonObject.setRings(lists);
        polygonObject.setSpatialReference(spatialReference);
        Gson gson = new Gson();
        return gson.toJson(polygonObject);
    }

    @Override
    public JSONObject getGj(String wkt) {
        if (wkt == null) {
            return null;
        }
        if (wkt.contains("(")) {
            int index = wkt.indexOf("(");
            String type = wkt.substring(0, index);
            String value = wkt.substring(index);
            String t = value.replace("(", "[").replace(")", "]");
//        Pattern.compile("-?(0-9){1,3}(.0-9)*+\\s+-?(0-9){1,2}(.0-9)*+");
//      Pattern compile = Pattern.compile("[-.0-9]+\\s+[-.0-9]+\\s+[-.0-9]+");
            Pattern compile = Pattern.compile("[-.0-9]+\\s+[-.0-9]+");
            Matcher matcher = compile.matcher(t);
            StringBuilder sb = new StringBuilder();
            sb.append("{\"type\":\"" + type + "\",\"coordinates\":");
            int end = 0;
            int start;
            while (matcher.find()) {
                String group = matcher.group();
                start = matcher.start();
                String[] split = group.split("\\s+");
                sb.append(t.substring(end, start)).append("[").append(split[0]).append(",").append
                        (split[1]).append("]");
                end = matcher.end();
            }
            sb.append(t.substring(end));
            sb.append("}");
            return JSONObject.fromObject(sb.toString());
        }
        return null;
    }

    @Override
    public String transformMultiPolygonToPolygon(JSONObject jsonObject){

        String coordinates = jsonObject.getString("coordinates");
        coordinates = coordinates.substring(1,coordinates.length()-1);
        jsonObject.put("coordinates",coordinates);
        String geojson = jsonObject.toString();
        geojson = geojson.replace("MULTIPOLYGON \"","Polygon\"");
        return geojson;
    }

    @Override
    public String transformPOLYGONToPolygon(JSONObject jsonObject){

        String geojson = jsonObject.toString();
        geojson = geojson.replace("POLYGON \"","Polygon\"");
        return geojson;

    }

    @Override
    public List<JSONObject> getAdvancedProductShpInfo(String path){


        //读取shp
        SimpleFeatureCollection colls1 = this.readShp(path);
        //拿到所有features
        SimpleFeatureIterator iters = colls1.features();
        //遍历打印
        List<JSONObject> jSONObjects = new ArrayList<JSONObject>();
        while (iters.hasNext()) {
            SimpleFeature sf = iters.next();
//            System.out.println(sf.getID() + " , " + sf.getAttributes());
//            System.out.println(sf.getAttribute("ImageFile"));
//            System.out.println(sf.getFeatureType());
//            System.out.println(sf.getProperties());
//            System.out.println("名字是"+sf.getName());
            String[] strList = (sf.getProperties()).toString().split("SimpleFeatureImpl.Attribute");
//            System.out.println("分割结果：");
            JSONObject jsonObject = new JSONObject();
            for (int i = 0; i < strList.length; i++) {
                String tmp = strList[i];
                System.out.println("原串是："+tmp);
                String key = "";
                String value = "";
                if (i > 0 && i < strList.length - 1) {
                    key = tmp.substring(2, tmp.indexOf('<'));
//                    System.out.println("key是：" + key);
                    String comma = ",";

                    if (comma.equals((tmp.charAt(tmp.length() - 1))))
                        value = "";
                    else
                        value = tmp.substring(tmp.lastIndexOf('=') + 1, tmp.lastIndexOf(','));
//                    System.out.println("value是：" + value);
                }
                if (i == strList.length - 1) {
                    {
                        key = tmp.substring(2, tmp.indexOf('<'));
//                        System.out.println("key是：" + key);
                        value = tmp.substring(tmp.lastIndexOf('=') + 1, tmp.lastIndexOf(']'));
//                        System.out.println("value是：" + value);
                    }
                }
                if (i != 0)
                    jsonObject.put(key, value);
            }
            jSONObjects.add(jsonObject);

        }
        List<JSONObject> jSONObjectList = new ArrayList<JSONObject>();
        for(JSONObject jsonObjectTmp : jSONObjects){

            String wkt = jsonObjectTmp.getString("the_geom");
            System.out.println("wkt为："+wkt);
            String multiPolygon = getGj(wkt).toString();
            System.out.println("multiPlygon是："+multiPolygon);
            String geojson = transformMultiPolygonToPolygon(getGj(wkt));
            System.out.println("geojson:"+geojson);
            System.out.println(jsonObjectTmp.getString("the_geom"));
//            JSONObject geoJsonOB = JSONObject.fromObject(jsonObjectTmp.getString("the_geom"));
//            geoJsonOB.put("type","Polygon");
//            geojson = geojson.replace("MULTIPOLYGON (","Polygon(");
            jsonObjectTmp.put("the_geom",geojson);
            jSONObjectList.add(jsonObjectTmp);
        }

        System.out.println("1111:"+jSONObjectList);
        return jSONObjectList;

    }

    @Override
    public String getShpFilePath(String parentPath){

        String shpPath = "";
        File file = new File(parentPath);
        File[] tempList = file.listFiles();
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                String tmp = tempList[i].toString();
                String postfix = tmp.substring(tmp.lastIndexOf('.')+1);
//                System.out.println(postfix);
                if(postfix.equals("shp")&&tmp.contains("cutline"))
                    shpPath = tmp;
            }
        }
        return shpPath;
    }

    @Override
    public String getBoundaryShpFilePath(String parentPath){

        String shpPath = "";
        File file = new File(parentPath);
        File[] tempList = file.listFiles();
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                String tmp = tempList[i].toString();
                String postfix = tmp.substring(tmp.lastIndexOf('.')+1);
//                System.out.println(postfix);
                if(postfix.equals("shp")&&tmp.contains("boundary"))
                    shpPath = tmp;
            }
        }
        return shpPath;
    }

    @Override
    public String getTifFilePath(String parentPath){

        String shpPath = "";
        File file = new File(parentPath);
        File[] tempList = file.listFiles();
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                String tmp = tempList[i].toString();
                String postfix = tmp.substring(tmp.lastIndexOf('.')+1);
                if(postfix.equals("tif") || postfix.equals("TIF"))
                    shpPath = tmp;
            }
        }
        return shpPath;
    }

    @Override
    public int updateProductLayerInfo(PdmProductLayerInfo pdmProductLayerInfo){

       return pdmProductLayerInfoMapper.insert(pdmProductLayerInfo);
    }

    @Override
    public void updateThemeticProductDetailImgGeo(String productId,String singleId,String geoJson){

        pdmThemeticProductDetailInfoMapper.updateThemeticProductDetailImgGeo(productId,singleId,geoJson);
    }
}








