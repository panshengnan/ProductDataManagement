package com.cgwx.service;

import com.cgwx.data.dto.SecondaryFileStructure;
import com.cgwx.data.dto.UploadFileReturn;
import com.cgwx.data.entity.PdmProductInfo;
import com.cgwx.data.entity.PdmThemeticProductDetailIndustryInfo;
import com.cgwx.data.entity.PdmThemeticProductDetailInfo;
import com.cgwx.data.entity.PdmThemeticProductInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;

import java.io.File;
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
    void copyFolder(String oldPath, String newPath);
    void copyFile(File source, File dest);
    UploadFileReturn uploadFile(MultipartFile file);
    String unZip(String fileName, String filePath);
    void zip(String srcFile, String dest, String passwd);
    List<String> getClientNameList(String clientName);
    List<String> getDeliverNameList(String deliverName);
    List<String> getProducerList(String producer);
    int updateProductInfoForTheme(PdmProductInfo pdmProductInfo);
    String getThemeticProductName(String tempId);
    String getThemeticProductTemporaryPath(String tempId);
    int updateThemeticProductDetailIndustry(PdmThemeticProductDetailIndustryInfo pdmThemeticProductDetailIndustryInfo);
    int updateThemeticProductDetail(PdmThemeticProductDetailInfo pdmThemeticProductDetailInfo);
    int updateThemeticProduct(PdmThemeticProductInfo pdmThemeticProductInfo);
    int insertPdmProducerInfo(String producerName);
    int selectCountByProducerName(String producerName);
    int updatePdmProducerInfo(String producerName);

}
