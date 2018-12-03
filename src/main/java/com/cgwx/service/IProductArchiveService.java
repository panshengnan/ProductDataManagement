package com.cgwx.service;

import com.cgwx.data.dto.ArchivalRecordsItems;
import com.cgwx.data.dto.SecondaryFileStructure;
import com.cgwx.data.dto.UploadFileReturn;
import com.cgwx.data.entity.*;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;

/**
 * Created by PanSN on 2018/9/5.
 */


public interface IProductArchiveService {              //

    void updateXml(Document document, PdmThemeticProductInfo pdmThemeticProductInfo);
    void update(Document document, String fileName);
    String changeTiftoJpg(String fileName);
    String ZoomImg(String fileName);
    String getUUId();
    String getNextProductId(int productType);
    List<String> getFileNameList(String productId);
    SecondaryFileStructure getSecondaryFileStructure(String path);
    SecondaryFileStructure getSecondaryFileStructureAndWriteCheckTable(String path,String archivePersonnel,int type);
    void copyFolder(String oldPath, String newPath);
    void copyFile(File source, File dest);
    UploadFileReturn uploadFile(MultipartFile file);
    String unZip(String fileName, String filePath);
    void zip(String srcFile, String dest, String passwd);
    List<String> getClientNameList(String clientName);
    List<String> getDeliverNameList(String deliverName);
    List<String> getProducerList(String producer);
    int updateProductInfo(PdmProductInfo pdmProductInfo);
    String getProductName(String tempId);
    String getThemeticProductTemporaryPath(String tempId);
    int updateThemeticProductDetailIndustry(PdmThemeticProductDetailIndustryInfo pdmThemeticProductDetailIndustryInfo);
    int updateThemeticProductDetail(PdmThemeticProductDetailInfo pdmThemeticProductDetailInfo);
    int updateThemeticProduct(PdmThemeticProductInfo pdmThemeticProductInfo);
    int insertPdmProducerInfo(String producerName);
    int selectCountByProducerName(String producerName);
    int updatePdmProducerInfo(String producerName);
    PageInfo<PdmArchiveRecordsInfo> getArchiveRecordList(String archivePersonnel, int curPageNum, int maxResult, String productName, int archiveType, int archiveStatus);
    int updateArchiveRecordsInfo(PdmArchiveRecordsInfo pdmArchiveRecordsInfo,String tempId,int productType);
    String getArchivePersonnelName(String archivePersonnel);
    String writeArchiveRecordAndWriteArchiveCheckInfo(String path,String archivePersonnel,int type);
    int updateOrthoProduct(PdmOrthoProductInfo pdmOrthoProductInfo);
    int updateInlayProduct(PdmInlayProductInfo pdmInlayProductInfo);
    int updateSubdivisionProduct(PdmSubdivisionProductInfo pdmSubdivisionProductInfo);
    int updateAdvancedProductShpInfo(PdmAdvancedProductShpInfo pdmAdvancedProductShpInfo);
    String xml2jsonString(String path)throws JSONException, IOException;

}
