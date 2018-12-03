package com.cgwx.service;

import com.cgwx.aop.result.Result;
import com.cgwx.data.entity.PdmProductLayerInfo;
import net.sf.json.JSONObject;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.operation.TransformException;
import org.springframework.stereotype.Service;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;


public interface LayerPublishService {

    Result publishTifToGeoserver(JSONObject jsonObject) throws ParserConfigurationException, IOException;
    JSONObject publishTif(String filePath, String nameSpace, String borderColor) throws IOException, FactoryException, TransformException;
    SimpleFeatureCollection readShp(String path );
    String getMULTIPOLYGONWktToJson(String wkt, int wkid);//存在兼容问题
    JSONObject getGj(String wkt);
    List<JSONObject> getAdvancedProductShpInfo(String path);
    String transformMultiPolygonToPolygon(JSONObject jsonObject);
    String transformPOLYGONToPolygon(JSONObject jsonObject);
    String getShpFilePath(String parentPath);
    String getBoundaryShpFilePath(String parentPath);
    String getTifFilePath(String parentPath);
    int updateProductLayerInfo(PdmProductLayerInfo pdmProductLayerInfo);
    void updateThemeticProductDetailImgGeo(String productId,String singleId,String geoJson);
}
