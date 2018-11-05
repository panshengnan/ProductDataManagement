package com.cgwx.service.impl;

import com.cgwx.aop.result.Result;
import com.cgwx.aop.result.ResultUtil;
import com.cgwx.common.constants.LayerInfo;
import com.cgwx.common.utils.GeoserverXml;
import com.cgwx.common.utils.PublishLayer;
import com.cgwx.service.LayerPublishService;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.gce.geotiff.GeoTiffFormat;
import org.geotools.gce.geotiff.GeoTiffReader;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
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
import java.net.URLEncoder;


@Service
public class LayerPublishServiceImpl implements LayerPublishService
{
    @Autowired
    LayerInfo layerInfo;

    @Override
    public Result publishTifToGeoserver(JSONObject jsonObject) throws ParserConfigurationException, IOException {
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
                return ResultUtil.error(511,"存在同名图层");
            }
            else
            {
                System.out.println(postMethod.getResponseBodyAsString());
                System.out.println(result);
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
    public Result publishTif(String filePath, String nameSpace, String borderColor) throws IOException, FactoryException, TransformException {
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
            return ResultUtil.error(510, "Tif文件无法打开");
        }
        GridCoverage2D coverage = reader.read(null);
        String crs = coverage.getCoordinateReferenceSystem().toString();
        if (crs == null) {
            return ResultUtil.error(510, "文件缺少坐标系");
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
        com.vividsolutions.jts.geom.Polygon polygon = JTS.toGeometry(envelope1);
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
        try {
            Result result= publishTifToGeoserver(jsonObject);
            if(result.getStatus()==200)
            {
                JSONObject param=new JSONObject();

                param.put("layerName",nameSpace+":"+name);
                if(coverage.getCoordinateReferenceSystem().toString().contains("UTM"))param.put("projection","UTM");
                else param.put("projection","WGS-84");
                return   ResultUtil.success(param.toString());
            }
            return result;

        } catch (ParserConfigurationException e) {
            return ResultUtil.error(511,"发布失败");
        }

    }


}
