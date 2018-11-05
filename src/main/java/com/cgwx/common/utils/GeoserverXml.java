package com.cgwx.common.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class GeoserverXml
{
    public static Document getADocument() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.newDocument();
    }
    public static String toStringFromDoc(Document document) {
        String result = null;

        if (document != null) {
            StringWriter strWtr = new StringWriter();
            StreamResult strResult = new StreamResult(strWtr);
            TransformerFactory tfac = TransformerFactory.newInstance();
            try {
                javax.xml.transform.Transformer t = tfac.newTransformer();
                t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                t.setOutputProperty(OutputKeys.INDENT, "yes");
                t.setOutputProperty(OutputKeys.METHOD, "xml"); // xml, html,
                // text
                t.setOutputProperty(
                        "{http://xml.apache.org/xslt}indent-amount", "4");
                t.transform(new DOMSource(document.getDocumentElement()),
                        strResult);
            } catch (Exception e) {
                System.err.println("XML.toString(Document): " + e);
            }
            result = strResult.getWriter().toString();
            try {
                strWtr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
    public static String GetDataStoreXml(String storeName,String fileUrl,String namespace) throws ParserConfigurationException, UnsupportedEncodingException {
        Document doc=getADocument();

        Element dataStore=doc.createElement("dataStore");
        doc.appendChild(dataStore);
        Element name=doc.createElement("name");
        name.appendChild(doc.createTextNode(storeName));
        System.out.println("字符串编码测试"+storeName.equals(new String(storeName.getBytes("UTF-8"),"UTF-8")));
        dataStore.appendChild(name);
        Element type=doc.createElement("type");
        type.appendChild(doc.createTextNode("Shapefile"));
        dataStore.appendChild(type);
        Element enabled=doc.createElement("enabled");
        enabled.appendChild(doc.createTextNode("true"));
        dataStore.appendChild(enabled);
        Element connectionParameters=doc.createElement("connectionParameters");
        dataStore.appendChild(connectionParameters);
        Element entry1=doc.createElement("entry") ;
        entry1.setAttribute("key","memory mapped buffer");
        entry1.appendChild(doc.createTextNode("false"));
        connectionParameters.appendChild(entry1);
        Element entry2=doc.createElement("entry");
        entry2.setAttribute("key","create spatial index");
        entry2.appendChild(doc.createTextNode("true"));
        connectionParameters.appendChild(entry2);
        Element entry3=doc.createElement("entry");
        entry3.setAttribute("key","charset");
        entry3.appendChild(doc.createTextNode("UTF-8"));
        connectionParameters.appendChild(entry3);
        Element entry4=doc.createElement("entry");
        entry4.setAttribute("key","fstype");
        entry4.appendChild(doc.createTextNode("shape"));
        connectionParameters.appendChild(entry4);
        Element entry5=doc.createElement("entry");
        entry5.setAttribute("key","cache and reuse memory maps");
        entry5.appendChild(doc.createTextNode("true"));
        connectionParameters.appendChild(entry5);
        Element entry6=doc.createElement("entry");
        entry6.setAttribute("key","url");
        entry6.appendChild(doc.createTextNode("file://"+fileUrl));
        connectionParameters.appendChild(entry6);
        Element entry7=doc.createElement("entry");
        entry7.setAttribute("key","namespace");
        entry7.appendChild(doc.createTextNode(namespace));
        connectionParameters.appendChild(entry7);
        Element entry8=doc.createElement("entry");
        entry8.setAttribute("key","enable spatial index");
        entry8.appendChild(doc.createTextNode("true"));
        connectionParameters.appendChild(entry8);
        Element _default=doc.createElement("__default");
        _default.appendChild(doc.createTextNode("false"));
        dataStore.appendChild(_default);

        return toStringFromDoc(doc)  ;
    }

    public static String GetFeatureTypeXml(JSONObject jsonObject) throws ParserConfigurationException, UnsupportedEncodingException {
        Document doc=getADocument();
        Element featureType=doc.createElement("featureType");
        doc.appendChild(featureType);
        Element name=doc.createElement("name");
        name.appendChild(doc.createTextNode(jsonObject.getString("layername")));
	    featureType.appendChild(name);

        Element nativeName=doc.createElement("nativeName");
        nativeName.appendChild(doc.createTextNode(jsonObject.getString("filename")));
	    featureType.appendChild(nativeName);

        Element namespace=doc.createElement("namespace");
        featureType.appendChild(namespace);
        Element name2=doc.createElement("name");
        namespace.appendChild(name2);
        name2.appendChild(doc.createTextNode(jsonObject.getString("namespace")));
        Element atom=doc.createElement("atom:link");
         atom.setAttribute("xmlns:atom","http://www.w3.org/2005/Atom");
        atom.setAttribute("rel","alternate");
        atom.setAttribute("href",jsonObject.getString("geoserverPath")+"/rest/namespaces/"+jsonObject.getString("namespace")+".xml");
        atom.setAttribute("type","application/xml");
        namespace.appendChild(atom);
        Element title=doc.createElement("title");
        featureType.appendChild(title);
        title.appendChild(doc.createTextNode(jsonObject.getString("layername")));
        Element keywords=doc.createElement("keywords");
	    featureType.appendChild(keywords);
        Element  nativeCRS=doc.createElement("nativeCRS");
        featureType.appendChild(nativeCRS);
        nativeCRS.appendChild(doc.createTextNode(jsonObject.getString("nativeCRS")));
        Element srs=doc.createElement("srs");
        srs.appendChild(doc.createTextNode(jsonObject.getString("srs")));
        featureType.appendChild(srs);
        Element nativeBoundingBox=doc.createElement("nativeBoundingBox");
        Element minx=doc.createElement("minx");
        minx.appendChild(doc.createTextNode(((JSONArray)jsonObject.get("nativeBoundingBox")).get(0).toString()));
        Element maxx=doc.createElement("maxx");
        maxx.appendChild(doc.createTextNode(((JSONArray)jsonObject.get("nativeBoundingBox")).get(1).toString()));
        Element miny=doc.createElement("miny");
        miny.appendChild(doc.createTextNode(((JSONArray)jsonObject.get("nativeBoundingBox")).get(2).toString()));
        Element maxy=doc.createElement("maxy");
        maxy.appendChild(doc.createTextNode(((JSONArray)jsonObject.get("nativeBoundingBox")).get(3).toString()));
        nativeBoundingBox.appendChild(minx);
        nativeBoundingBox.appendChild(maxx);
        nativeBoundingBox.appendChild(miny);
        nativeBoundingBox.appendChild(maxy);
        Element  crs=doc.createElement("crs");
        crs.appendChild(doc.createTextNode(jsonObject.getString("srs")));
        nativeBoundingBox.appendChild(crs);
        featureType.appendChild(nativeBoundingBox);

        Element latLonBoundingBox=doc.createElement("latLonBoundingBox");
        Element minx2=doc.createElement("minx");
        minx2.appendChild(doc.createTextNode(((JSONArray)jsonObject.get("latLonBoundingBox")).get(0).toString()));
        Element maxx2=doc.createElement("maxx");
        maxx2.appendChild(doc.createTextNode(((JSONArray)jsonObject.get("latLonBoundingBox")).get(1).toString()));
        Element miny2=doc.createElement("miny");
        miny2.appendChild(doc.createTextNode(((JSONArray)jsonObject.get("latLonBoundingBox")).get(2).toString()));
        Element maxy2=doc.createElement("maxy");
        maxy2.appendChild(doc.createTextNode(((JSONArray)jsonObject.get("latLonBoundingBox")).get(3).toString()));
        latLonBoundingBox.appendChild(minx2);
        latLonBoundingBox.appendChild(maxx2);
        latLonBoundingBox.appendChild(miny2);
        latLonBoundingBox.appendChild(maxy2);
        Element  crs2=doc.createElement("crs");
        crs2.appendChild(doc.createTextNode("EPSG:4326"));
        latLonBoundingBox.appendChild(crs2);
        featureType.appendChild(latLonBoundingBox);
        Element projectionPolicy=doc.createElement("projectionPolicy");
        projectionPolicy.appendChild(doc.createTextNode("FORCE_DECLARED"));
        featureType.appendChild(projectionPolicy);
        Element enabled=doc.createElement("enabled");
        enabled.appendChild(doc.createTextNode("true"));
        featureType.appendChild(enabled);
        Element metadata=doc.createElement("metadata");
        Element entry1=doc.createElement("entry");
        entry1.setAttribute("key","elevation");
        metadata.appendChild(entry1);
        Element dimensionInfo=doc.createElement("dimensionInfo");
        entry1.appendChild(dimensionInfo);
        Element enabled2=doc.createElement("enabled");
        enabled2.appendChild(doc.createTextNode("false"));
        dimensionInfo.appendChild(enabled2);
        Element entry2=doc.createElement("entry");
        entry2.setAttribute("key","time");
        metadata.appendChild(entry2);
        Element dimensionInfo2=doc.createElement("dimensionInfo");
        entry2.appendChild(dimensionInfo2);
        Element enabled3=doc.createElement("enabled");
        enabled3.appendChild(doc.createTextNode("false"));
        dimensionInfo2.appendChild(enabled3);
        Element defaultValue=doc.createElement("defaultValue");
        dimensionInfo2.appendChild(defaultValue);
        Element entry3=doc.createElement("entry");
        entry3.setAttribute("key","cachingEnabled");
        entry3.appendChild(doc.createTextNode("false"));
        metadata.appendChild(entry3);
        featureType.appendChild(metadata);
        Element store=doc.createElement("store");
        Element name3=doc.createElement("name");
        store.setAttribute("class","dataStore");
        name3.appendChild(doc.createTextNode(jsonObject.getString("namespace")+":"+jsonObject.getString("storename")));
	    store.appendChild(name3);
        Element atom2=doc.createElement("atom:link");
        atom2.setAttribute("xmlns:atom","http://www.w3.org/2005/Atom");
        atom2.setAttribute("rel","alternate");
        atom2.setAttribute("href",jsonObject.getString("geoserverPath")+"/rest/workspaces/"+jsonObject.getString("namespace")+"/datastores/"+ URLEncoder.encode(jsonObject.getString("storename"),"utf-8")+".xml");
        atom2.setAttribute("type","application/xml");
        store.appendChild(atom2);
        featureType.appendChild(store);
        Element maxFeatures=doc.createElement("maxFeatures");
        maxFeatures.appendChild(doc.createTextNode("0"));
        featureType.appendChild(maxFeatures);
        Element numDecimals=doc.createElement("numDecimals");
        numDecimals.appendChild(doc.createTextNode("0"));
        featureType.appendChild(numDecimals);
        Element overridingServiceSRS=doc.createElement("overridingServiceSRS");
        overridingServiceSRS.appendChild(doc.createTextNode("false"));
        featureType.appendChild(overridingServiceSRS);
        Element skipNumberMatched=doc.createElement("skipNumberMatched");
        skipNumberMatched.appendChild(doc.createTextNode("false"));
        featureType.appendChild(skipNumberMatched);

        Element circularArcPresent=doc.createElement("circularArcPresen");
        circularArcPresent.appendChild(doc.createTextNode("false"));
        featureType.appendChild(circularArcPresent);

        Element attributes=doc.createElement("attributes");
        Element attribute=doc.createElement("attribute");
        attributes.appendChild(attribute);
        Element name4=doc.createElement("name");
        attribute.appendChild(name4);
        name4.appendChild(doc.createTextNode("the_geom"));

        Element minOccurs=doc.createElement("minOccurs");
        attribute.appendChild(minOccurs);
        minOccurs.appendChild(doc.createTextNode("0"));

        Element maxOccurs=doc.createElement("maxOccurs");
        attribute.appendChild(maxOccurs);
        maxOccurs.appendChild(doc.createTextNode("1"));

        Element nillable=doc.createElement("nillable");
        attribute.appendChild(nillable);
        nillable.appendChild(doc.createTextNode("true"));
        Element binding=doc.createElement("binding");
        attribute.appendChild(binding);
        binding.appendChild(doc.createTextNode("com.vividsolutions.jts.geom.MultiPolygon"));

        featureType.appendChild(attributes);
        return toStringFromDoc(doc) ;
   }
public static String GetCoveragestoreXml(JSONObject jsonObject) throws ParserConfigurationException {
        Document doc=getADocument();
        Element coverageStore=doc.createElement("coverageStore");
        doc.appendChild(coverageStore);

        Element name=doc.createElement("name");
        name.appendChild(doc.createTextNode(jsonObject.getString("storename")));
        coverageStore.appendChild(name);

        Element type=doc.createElement("type");
        type.appendChild(doc.createTextNode("GeoTIFF"));
        coverageStore.appendChild(type);

        Element enabled=doc.createElement("enabled");
        enabled.appendChild(doc.createTextNode("true"));
        coverageStore.appendChild(enabled);

        Element workspace=doc.createElement("workspace");

        coverageStore.appendChild(workspace);

        Element name2=doc.createElement("name");
        name2.appendChild(doc.createTextNode(jsonObject.getString("namespace")));
        workspace.appendChild(name2);
        Element atom1=doc.createElement("atom:link");
        atom1.setAttribute("xmlns:atom","http://www.w3.org/2005/Atom");
        atom1.setAttribute("rel","alternate");
        atom1.setAttribute("href",jsonObject.getString("geoserverPath")+"/rest/workspaces/"+jsonObject.getString("namespace")+".xml");
        atom1.setAttribute("type","application/xml");
        workspace.appendChild(atom1);

        Element _default=doc.createElement("__default__");
        _default.appendChild(doc.createTextNode("false"));
            coverageStore.appendChild(_default);

        Element  url=doc.createElement("url");
        url.appendChild(doc.createTextNode("file://"+jsonObject.getString("filepath")));
        coverageStore.appendChild(url);

        Element coverages=doc.createElement("coverages");
        Element  atom2=doc.createElement("atom:link");
        atom2.setAttribute("xmlns:atom","http://www.w3.org/2005/Atom");
        atom2.setAttribute("rel","alternate");
        atom2.setAttribute("href",jsonObject.getString("geoserverPath")+"/rest/workspaces/"+jsonObject.getString("namespace")+"/coveragestores/"+jsonObject.getString("storename")+"/coverages.xml");
        atom2.setAttribute("type","application/xml");
        coverages.appendChild(atom2);
        coverageStore.appendChild(coverages);
         return toStringFromDoc(doc);
    }
    public static  String getCoverageXml(JSONObject jsonObject) throws ParserConfigurationException {
        Document doc=getADocument();
        Element  coverage=doc.createElement("coverage");
        doc.appendChild(coverage);
        Element  name=doc.createElement("name");
        name.appendChild(doc.createTextNode(jsonObject.getString("layername")));
        coverage.appendChild(name);

        Element  nativeName=doc.createElement("nativeName");
        nativeName.appendChild(doc.createTextNode(jsonObject.getString("filename")));
        coverage.appendChild(nativeName);


        Element  namespace=doc.createElement("namespace");
        coverage.appendChild(namespace);
        Element  name2=doc.createElement("name");
        namespace.appendChild(name2);
        name2.appendChild(doc.createTextNode(jsonObject.getString("namespace")));
        Element   atom=doc.createElement("atom:link");
        atom.setAttribute("xmlns:atom","http://www.w3.org/2005/Atom");
        atom.setAttribute("rel","alternate");
        atom.setAttribute("href",jsonObject.getString("geoserverPath")+"/rest/namespaces/"+jsonObject.getString("namespace")+".xml");
        atom.setAttribute("type","application/xml");
        namespace.appendChild(atom);

        Element  title=doc.createElement("title");
        coverage.appendChild(title);
        title.appendChild(doc.createTextNode(jsonObject.getString("layername")));
        Element  description=doc.createElement("description");
        coverage.appendChild(description);
        description.appendChild(doc.createTextNode("Generated from GeoTIFF"));

        Element  keywords=doc.createElement("keywords");
        coverage.appendChild(keywords);
        Element  string1=doc.createElement("string");
        keywords.appendChild(string1);
        string1.appendChild(doc.createTextNode("geotiff_coverage"));
        Element  string2=doc.createElement("string");
        keywords.appendChild(string2);
        string2.appendChild(doc.createTextNode("WCS"));
        Element  string3=doc.createElement("string");
        keywords.appendChild(string3);
        string3.appendChild(doc.createTextNode("GeoTIFF"));

        Element  nativeCRS=doc.createElement("nativeCRS");
        coverage.appendChild(nativeCRS);
        nativeCRS.appendChild(doc.createTextNode(jsonObject.getString("nativeCRS")));

        Element  srs=doc.createElement("srs");
        srs.appendChild(doc.createTextNode(jsonObject.getString("srs")));
        coverage.appendChild(srs);

        Element  nativeBoundingBox=doc.createElement("nativeBoundingBox");
        Element  minx=doc.createElement("minx");
       JSONArray jsonArray1=(JSONArray)jsonObject.get("nativeBoundingBox");
        minx.appendChild(doc.createTextNode(String.valueOf(jsonArray1.get(0))))	;
        Element  maxx=doc.createElement("maxx");
        maxx.appendChild(doc.createTextNode(String.valueOf(jsonArray1.get(1))));
        Element  miny=doc.createElement("miny");
        miny.appendChild(doc.createTextNode(String.valueOf(jsonArray1.get(2))));
        Element  maxy=doc.createElement("maxy");
        maxy.appendChild(doc.createTextNode(String.valueOf(jsonArray1.get(3))));
        nativeBoundingBox.appendChild(minx);
        nativeBoundingBox.appendChild(maxx);
        nativeBoundingBox.appendChild(miny);
        nativeBoundingBox.appendChild(maxy);
        Element  crs=doc.createElement("crs");
        crs.appendChild(doc.createTextNode(jsonObject.getString("srs")));
        nativeBoundingBox.appendChild(crs);
        coverage.appendChild(nativeBoundingBox);
        JSONArray jsonArray2=(JSONArray)jsonObject.get("latLonBoundingBox");
        Element  latLonBoundingBox=doc.createElement("latLonBoundingBox");
        Element  minx2=doc.createElement("minx");
          minx2.appendChild(doc.createTextNode(String.valueOf(jsonArray2.get(0))));
        Element  maxx2=doc.createElement("maxx");
        maxx2.appendChild(doc.createTextNode(String.valueOf(jsonArray2.get(1))));
        Element  miny2=doc.createElement("miny");
        miny2.appendChild(doc.createTextNode(String.valueOf(jsonArray2.get(2))));
        Element   maxy2=doc.createElement("maxy");
        maxy2.appendChild(doc.createTextNode(String.valueOf(jsonArray2.get(3))));
        latLonBoundingBox.appendChild(minx2);
        latLonBoundingBox.appendChild(maxx2);
        latLonBoundingBox.appendChild(miny2);
        latLonBoundingBox.appendChild(maxy2);
        Element  crs2=doc.createElement("crs");
        crs2.appendChild(doc.createTextNode("EPSG:4326"));
        latLonBoundingBox.appendChild(crs2);
        coverage.appendChild(latLonBoundingBox);

        Element  projectionPolicy=doc.createElement("projectionPolicy");
        projectionPolicy.appendChild(doc.createTextNode("REPROJECT_TO_DECLARED"));
        coverage.appendChild(projectionPolicy);

        Element  enabled=doc.createElement("enabled");
        enabled.appendChild(doc.createTextNode("true"));
        coverage.appendChild(enabled);

        Element  metadata=doc.createElement("metadata");
        coverage.appendChild(metadata);

        Element  store=doc.createElement("store");
        coverage.appendChild(store);
        store.setAttribute("class","coverageStore");
        Element  name3=doc.createElement("name");
        name3.appendChild(doc.createTextNode(jsonObject.getString("namespace")+":"+jsonObject.getString("storename")));
        store.appendChild(name3);

        Element  atom2=doc.createElement("atom:link");
        atom2.setAttribute("xmlns:atom","http://www.w3.org/2005/Atom");
        atom2.setAttribute("rel","alternate");
        atom2.setAttribute("href",jsonObject.getString("geoserverPath")+"/rest/workspaces/"+jsonObject.getString("namespace")+"/coveragestores/"+jsonObject.getString("storename")+".xml");
        atom2.setAttribute("type","application/xml");
        store.appendChild(atom2);

        Element  nativeFormat=doc.createElement("nativeFormat");
        nativeFormat.appendChild(doc.createTextNode("GeoTIFF"));
        coverage.appendChild(nativeFormat);

        Element  grid=doc.createElement("grid");
        coverage.appendChild(grid);
        grid.setAttribute("dimension","2");
        Element  range=doc.createElement("range");
        grid.appendChild(range);
        Element  low=doc.createElement("low");
        range.appendChild(low);
        low.appendChild(doc.createTextNode("0 0"));
        Element  high=doc.createElement("high");
        range.appendChild(high);
        high.appendChild(doc.createTextNode(jsonObject.getString("rasterXSize")+" "+jsonObject.getString("rasterYSize")));
        Element  transform=doc.createElement("transform");
        grid.appendChild(transform);
        Element  scaleX=doc.createElement("scaleX");
        transform.appendChild(scaleX);
        scaleX.appendChild(doc.createTextNode(jsonObject.getString("scaleX")));
        Element  scaleY=doc.createElement("scaleY");
        transform.appendChild(scaleY);
        scaleY.appendChild(doc.createTextNode(jsonObject.getString("scaleY")));
        Element  shearX=doc.createElement("shearX");
        transform.appendChild(shearX);
        shearX.appendChild(doc.createTextNode(jsonObject.getString("shearX")));
        Element  shearY=doc.createElement("shearY");
        transform.appendChild(shearY);
        shearY.appendChild(doc.createTextNode(jsonObject.getString("shearY")));
        Element  translateX=doc.createElement("translateX");
        transform.appendChild(translateX);
        translateX.appendChild(doc.createTextNode(jsonObject.getString("translateX")));
        Element  translateY=doc.createElement("translateY");
        transform.appendChild(translateY);
        translateY.appendChild(doc.createTextNode(jsonObject.getString("translateY")));
        Element  crs3=doc.createElement("crs");
        crs3.appendChild(doc.createTextNode(jsonObject.getString("srs")));
        grid.appendChild(crs3);

        Element  supportedFormats=doc.createElement("supportedFormats");
        coverage.appendChild(supportedFormats);
        Element  string4=doc.createElement("string");
        string4.appendChild(doc.createTextNode("ImageMosaic"));
        supportedFormats.appendChild(string4);
        Element  string5=doc.createElement("string");
        string5.appendChild(doc.createTextNode("GIF"));
        supportedFormats.appendChild(string5);
        Element  string6=doc.createElement("string");
        string6.appendChild(doc.createTextNode("PNG"));
        supportedFormats.appendChild(string6);
        Element  string7=doc.createElement("string");
        string7.appendChild(doc.createTextNode("JPEG"));
        supportedFormats.appendChild(string7);
        Element   string8=doc.createElement("string");
        string8.appendChild(doc.createTextNode("TIFF"));
        supportedFormats.appendChild(string8);
        Element  string9=doc.createElement("string");
        string9.appendChild(doc.createTextNode("ArcGrid"));
        supportedFormats.appendChild(string9);
        Element  string10=doc.createElement("string");
        string10.appendChild(doc.createTextNode("Gtopo30"));
        supportedFormats.appendChild(string10);
        Element  string11=doc.createElement("string");
        string11.appendChild(doc.createTextNode("GEOTIFF"));
        supportedFormats.appendChild(string11);


        Element  interpolationMethods=doc.createElement("interpolationMethods");
        coverage.appendChild(interpolationMethods);
        Element  string12=doc.createElement("string");
        interpolationMethods.appendChild(string12);
        string12.appendChild(doc.createTextNode("nearest neighbor"));
        Element  string13=doc.createElement("string");
        interpolationMethods.appendChild(string13);
        string13.appendChild(doc.createTextNode("bilinear"));
        Element  string14=doc.createElement("string");
        interpolationMethods.appendChild(string14);
        string14.appendChild(doc.createTextNode("bicubic"));

        Element  defaultInterpolationMethod=doc.createElement("defaultInterpolationMethod");
        defaultInterpolationMethod.appendChild(doc.createTextNode("nearest neighbor"));
        coverage.appendChild(defaultInterpolationMethod);



        Element  requestSRS=doc.createElement("requestSRS");
        coverage.appendChild(requestSRS);
        Element  string15=doc.createElement("string");
        requestSRS.appendChild(string15);
        string15.appendChild(doc.createTextNode(jsonObject.getString("srs")));

        Element   responseSRS=doc.createElement("responseSRS");
        coverage.appendChild(responseSRS);
        Element  string16=doc.createElement("string");
        responseSRS.appendChild(string16);
        string16.appendChild(doc.createTextNode(jsonObject.getString("srs")));


        Element   parameters=doc.createElement("parameters");
        coverage.appendChild(parameters);
        Element  entry1=doc.createElement("entry");
        parameters.appendChild(entry1);
        Element  string17=doc.createElement("string");
        string17.appendChild(doc.createTextNode("InputTransparentColor"));
        entry1.appendChild(string17);
        Element   colorString=doc.createElement("string");
        colorString.appendChild(doc.createTextNode(jsonObject.getString("borderColor")));
        entry1.appendChild(colorString);

        Element  entry2=doc.createElement("entry");
        parameters.appendChild(entry2);
        Element  string18=doc.createElement("string");
        string18.appendChild(doc.createTextNode("SUGGESTED_TILE_SIZE"));
        entry2.appendChild(string18);
        Element  string19=doc.createElement("string");
        string19.appendChild(doc.createTextNode("256,256"));
        entry2.appendChild(string19);



        return toStringFromDoc(doc);
    }
}
