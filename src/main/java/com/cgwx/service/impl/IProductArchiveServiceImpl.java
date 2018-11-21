package com.cgwx.service.impl;

import com.cgwx.dao.*;
import com.cgwx.data.dto.ArchivalRecordsItems;
import com.cgwx.data.dto.DirectoryInfo;
import com.cgwx.data.dto.SecondaryFileStructure;
import com.cgwx.data.dto.UploadFileReturn;
import com.cgwx.data.entity.*;
import com.cgwx.service.IProductArchiveService;
import com.cgwx.service.IProductDownloadService;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.JPEGEncodeParam;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by PanSN on 2018/9/5.
 */

@Service
public class IProductArchiveServiceImpl implements IProductArchiveService {

    @Autowired
    IProductDownloadService iProductDownloadService;

    @Autowired
    PdmThemeticProductInfoMapper pdmThemeticProductInfoMapper;

    @Autowired
    PdmOrthoProductInfoMapper pdmOrthoProductInfoMapper;

    @Autowired
    PdmInlayProductInfoMapper pdmInlayProductInfoMapper;

    @Autowired
    PdmSubdivisionProductInfoMapper pdmSubdivisionProductInfoMapper;

    @Autowired
    PdmArchiveCheckInfoMapper pdmArchiveCheckInfoMapper;

    @Autowired
    PdmProductInfoMapper pdmProductInfoMapper;

    @Autowired
    PdmThemeticProductDetailIndustryInfoMapper pdmThemeticProductDetailIndustryInfoMapper;

    @Autowired
    PdmThemeticProductDetailInfoMapper pdmThemeticProductDetailInfoMapper;

    @Autowired
    PdmProducerInfoMapper pdmProducerInfoMapper;

    @Autowired
    PdmArchiveRecordsInfoMapper pdmArchiveRecordsInfoMapper;

    @Autowired
    PdmUserInfoMapper pdmUserInfoMapper;


    @Override/*上传文件*/
    public UploadFileReturn uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            return null;
        }
        String fileName = file.getOriginalFilename();
        UploadFileReturn uploadFileReturn = new UploadFileReturn();
        String path = System.getProperty("user.dir") + "/临时存储区";
        File dest = new File(path + "/" + fileName);
        System.out.println("文件保存路径为：" + dest.toString());
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {

            file.transferTo(dest); //保存文件
            uploadFileReturn.setFileName(dest.toString());
            uploadFileReturn.setFilePath(path);
            return uploadFileReturn;

        } catch (IllegalStateException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override/*解压zip文件*/
    public String unZip(String source, String dest) {

        String password = "password";
        System.out.println("解压中……");
        try {
            File zipFile = new File(source);
            ZipFile zFile = new ZipFile(zipFile);
//            zFile.setFileNameCharset("GBK");
            zFile.setFileNameCharset("UTF-8");
            File destDir = new File(dest);     // 解压目录
            if (zFile.isEncrypted()) {
                zFile.setPassword(password.toCharArray());  // 设置密码
            }
            zFile.extractAll(dest);      // 将文件抽出到解压目录(解压)
            List<FileHeader> headerList = zFile.getFileHeaders();
            List<File> extractedFileList = new ArrayList<File>();
            for (FileHeader fileHeader : headerList) {
                if (!fileHeader.isDirectory()) {
                    extractedFileList.add(new File(destDir, fileHeader.getFileName()));
                }
            }
            File[] extractedFiles = new File[extractedFileList.size()];
            extractedFileList.toArray(extractedFiles);
            for (File f : extractedFileList) {
                System.out.println(f.getAbsolutePath() + "....");
            }
        } catch (ZipException e) {
            System.out.println("解压失败！");
        }

        String unzipPath = source;
        unzipPath = unzipPath.substring(0,unzipPath.indexOf('.'));
        return unzipPath;
    }

    @Override/*压缩zip文件*/
    public void zip(String srcFile, String dest, String passwd) {
        File srcfile = new File(srcFile);

        //创建目标文件
        String destname = buildDestFileName(srcfile, dest);
        ZipParameters par = new ZipParameters();
        par.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        par.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

        if (passwd != null) {
            par.setEncryptFiles(true);
            par.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
            par.setPassword(passwd.toCharArray());
        }

        try {
            ZipFile zipfile = new ZipFile(destname);
            if (srcfile.isDirectory()) {
                zipfile.addFolder(srcfile, par);
            } else {
                zipfile.addFile(srcfile, par);
            }
        } catch (ZipException e) {
        }
    }
    public static String buildDestFileName(File srcfile, String dest) {
        if (dest == null) {
            if (srcfile.isDirectory()) {
                dest = srcfile.getParent() + File.separator + srcfile.getName() + ".zip";
            } else {
                String filename = srcfile.getName().substring(0, srcfile.getName().lastIndexOf("."));
                dest = srcfile.getParent() + File.separator + filename + ".zip";
            }
        } else {
            createPath(dest);//路径的创建
            if (dest.endsWith(File.separator)) {
                String filename = "";
                if (srcfile.isDirectory()) {
                    filename = srcfile.getName();
                } else {
                    filename = srcfile.getName().substring(0, srcfile.getName().lastIndexOf("."));
                }
                dest += filename + ".zip";
            }
        }
        return dest;
    }
    public static void createPath(String dest) {
        File destDir = null;
        if (dest.endsWith(File.separator)) {
            destDir = new File(dest);//给出的是路径时
        } else {
            destDir = new File(dest.substring(0, dest.lastIndexOf(File.separator)));
        }

        if (!destDir.exists()) {
            destDir.mkdirs();
        }
    }

    @Override
    public void updateXml(Document document, PdmThemeticProductInfo pdmThemeticProductInfo) {

        //取出这个元素
        Element element = document.createElement("productData");
        //添加属性
        element.setAttribute("productId", "id1");
        Element element_name = document.createElement("name");
        element_name.setTextContent("2B");
//         Element element_nianling = doc.createElement("nianling");
//         element_nianling.setTextContent("23");
//         Element element_jieshao = doc.createElement("jieshao");
//         element_jieshao.setTextContent("gi sh");
        element.appendChild(element_name);
//         element.appendChild(element_nianling);
//         element.appendChild(element_jieshao);
        //添加这个元素
        document.getDocumentElement().appendChild(element);
    }

    @Override/*更新xml*/
    public void update(Document document, String fileName) {
        try {
            //创建一个TransformerFactory实例
            TransformerFactory tff = TransformerFactory.newInstance();
            //通过TransformerFactory 得到一个转换器
            Transformer tf = tff.newTransformer();
            //通过Transformer类的方法 transform(Source xmlSource, Result outputTarget)
            //将 XML Source 转换为 Result。
//            tf.transform(new DOMSource(document), new StreamResult("C:\\Users\\37753\\Desktop\\产品管理后台\\pdm\\专题产品\\长春市201309热岛效应\\长春热岛201607.xml"));
            tf.transform(new DOMSource(document), new StreamResult(fileName));
            System.out.println("写入完成！");
        } catch (Exception e) {
        }
    }

    @Override/*生成缩略图*/
    public String changeTiftoJpg(String fileName) {

        String jpgName = fileName.replace(".tif", ".jpg");
        try {
            RenderedOp src = JAI.create("fileLoad", fileName);
            OutputStream os = new FileOutputStream(jpgName);
            JPEGEncodeParam param = new JPEGEncodeParam();
            ImageEncoder enc = ImageCodec.createImageEncoder("JPEG", os, param);
            enc.encode(src);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jpgName;
    }

    @Override/*缩放img*/
    public String ZoomImg(String fileName) {

        String thumbnailImg = fileName.replace(".jpg", "Thumbnail.jpg");
        try {
            FileInputStream fis = new FileInputStream(fileName);
            BufferedImage bfimg = ImageIO.read(fis);
            int width = new Double(bfimg.getWidth()).intValue();
            int height = new Double(bfimg.getHeight()).intValue();
            if (width < 500) return fileName;
            int newHeight = height / 5;
            int newWidth = width / 5;
            BufferedImage bufferImgOut = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_3BYTE_BGR);
            Graphics graphics = bufferImgOut.createGraphics();
            graphics.drawImage(bfimg.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
            ImageIO.write(bufferImgOut, "jpg", new File(thumbnailImg));
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        File f = new File(fileName);
        f.delete();
        return thumbnailImg;
    }

    @Override
    public String getUUId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    @Override /*分别获取每种高级产品的id*/
    public String getNextProductId(int productType) {
        Date currentDate = new Date();
        SimpleDateFormat formatyyyyMMddHS = new SimpleDateFormat("yyyy-MM-dd 00:00:01");
        SimpleDateFormat formatyyyyMMdd = new SimpleDateFormat("yyyyMMdd");
        String currentDateStr = formatyyyyMMddHS.format(currentDate);
        int count = 0;
        String productId = "";
        String timestamp = "";
        switch (productType) {
            case 1://专题
                count = pdmThemeticProductInfoMapper.selectThemeticProductCountByDate(currentDateStr);
                timestamp = formatyyyyMMdd.format(new Date());
                productId = "THEME_PRODUCT_" + timestamp + "_" + (int) (Math.random() * 10000) + "_" + (new String(10001 + count + "").substring(1, 5));
                break;
            case 2://正射
                count = pdmOrthoProductInfoMapper.selectOrthoProductCountByDate(currentDateStr);
                timestamp = formatyyyyMMdd.format(new Date());
                productId = "ORTHO_PRODUCT_" + timestamp + "_" + (int) (Math.random() * 10000) + "_" + (new String(10001 + count + "").substring(1, 5));
                break;
            case 3://镶嵌
                count = pdmInlayProductInfoMapper.selectInlayProductCountByDate(currentDateStr);
                timestamp = formatyyyyMMdd.format(new Date());
                productId = "INLAY_PRODUCT_" + timestamp + "_" + (int) (Math.random() * 10000) + "_" + (new String(10001 + count + "").substring(1, 5));
                break;
            case 4://分幅
                count = pdmSubdivisionProductInfoMapper.selectSubdivisionProductCountByDate(currentDateStr);
                timestamp = formatyyyyMMdd.format(new Date());
                productId = "SUBDIVISION_PRODUCT_" + timestamp + "_" + (int) (Math.random() * 10000) + "_" + (new String(10001 + count + "").substring(1, 5));
                break;
            default:
                break;
        }

        return productId;
    }

    @Override /*获取一个文件夹中的所有文件*/
    public List<String> getFileNameList(String productId){

        String path = iProductDownloadService.getEntityFilePath(productId);
        List<String> files = new ArrayList<String>();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
              //System.out.println("文     件：" + tempList[i]);
              String tmp = tempList[i].toString();
              tmp = tmp.substring(tmp.lastIndexOf('\\')+1);
              System.out.println(tmp);
              files.add(tmp);
            }
            if (tempList[i].isDirectory()) {
             //System.out.println("文件夹：" + tempList[i]);
            }
        }
        return files;
    }

    @Override/*获取专题多期产品中二级目录中的目录结构*/
    public SecondaryFileStructure getSecondaryFileStructureAndWriteCheckTable(String path,String archivePersonnel,int type) {

        List<String> files = new ArrayList<String>();
        File file = new File(path);
        File[] tempList = file.listFiles();
        SecondaryFileStructure secondaryFileStructure = new SecondaryFileStructure();
        String tempId = getUUId();
        secondaryFileStructure.setTempId(tempId);
        List<DirectoryInfo> directoryInfoList = new ArrayList<DirectoryInfo>();
        int count = 0;
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                String tmp = tempList[i].toString();
                tmp = tmp.substring(tmp.lastIndexOf('\\') + 1);
                System.out.println(tmp);
                files.add(tmp);
            }
            if (tempList[i].isDirectory()) {
                DirectoryInfo directoryInfo = new DirectoryInfo();
                directoryInfo.setSingleTempId((count++)+"");
                List<String> files2 = new ArrayList<String>();
                String tmp = tempList[i].toString();
                tmp = tmp.substring(tmp.lastIndexOf('\\') + 1);
                directoryInfo.setDirectoryName(tmp);
                System.out.println(tmp);
                File[] tempList2 = tempList[i].listFiles();
                for (int j = 0; j < tempList2.length; j++) {
                    String tmp2 = tempList2[j].toString();
                    tmp2 = tmp2.substring(tmp2.lastIndexOf('\\') + 1);
                    System.out.println(tmp2);
                    files2.add(tmp2);

                }
                directoryInfo.setFileListInDirectory(files2);
                directoryInfoList.add(directoryInfo);
            }
        }
        secondaryFileStructure.setFile(files);
        secondaryFileStructure.setDirectory(directoryInfoList);
        PdmArchiveCheckInfo pdmArchiveCheckInfo = new PdmArchiveCheckInfo();
        pdmArchiveCheckInfo.setTemporaryPath(path);
        pdmArchiveCheckInfo.setProductId(tempId);
        pdmArchiveCheckInfo.setFileName(path.substring(path.lastIndexOf('\\')+1));
        pdmArchiveCheckInfo.setStatus(0);
        pdmArchiveCheckInfoMapper.insert(pdmArchiveCheckInfo);
        //操作一波归档记录表
        PdmArchiveRecordsInfo pdmArchiveRecordsInfo = new PdmArchiveRecordsInfo();
        pdmArchiveRecordsInfo.setArchiveResult(0);
        pdmArchiveRecordsInfo.setArchivePersonnel(archivePersonnel);
        pdmArchiveRecordsInfo.setProductName(path.substring(path.lastIndexOf('\\')+1));
        pdmArchiveRecordsInfo.setArchiveType(type);
        pdmArchiveRecordsInfo.setProductId(tempId);
        pdmArchiveRecordsInfo.setArchiveTime(new Date());
        pdmArchiveRecordsInfoMapper.insert(pdmArchiveRecordsInfo);
        System.out.println("路径是："+path.substring(path.lastIndexOf('\\')+1));
        //操作一波归档记录表
        return secondaryFileStructure;
    }

    @Override/*获取专题多期产品中二级目录中的目录结构*/
    public SecondaryFileStructure getSecondaryFileStructure(String path) {

        List<String> files = new ArrayList<String>();
        File file = new File(path);
        File[] tempList = file.listFiles();
        SecondaryFileStructure secondaryFileStructure = new SecondaryFileStructure();
        String tempId = getUUId();
        secondaryFileStructure.setTempId(tempId);
        List<DirectoryInfo> directoryInfoList = new ArrayList<DirectoryInfo>();
        int count = 0;
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                String tmp = tempList[i].toString();
                tmp = tmp.substring(tmp.lastIndexOf('\\') + 1);
                System.out.println(tmp);
                files.add(tmp);
            }
            if (tempList[i].isDirectory()) {
                DirectoryInfo directoryInfo = new DirectoryInfo();
                directoryInfo.setSingleTempId((count++)+"");
                List<String> files2 = new ArrayList<String>();
                String tmp = tempList[i].toString();
                tmp = tmp.substring(tmp.lastIndexOf('\\') + 1);
                directoryInfo.setDirectoryName(tmp);
                System.out.println(tmp);
                File[] tempList2 = tempList[i].listFiles();
                for (int j = 0; j < tempList2.length; j++) {
                    String tmp2 = tempList2[j].toString();
                    tmp2 = tmp2.substring(tmp2.lastIndexOf('\\') + 1);
                    System.out.println(tmp2);
                    files2.add(tmp2);

                }
                directoryInfo.setFileListInDirectory(files2);
                directoryInfoList.add(directoryInfo);
            }
        }
        secondaryFileStructure.setFile(files);
        secondaryFileStructure.setDirectory(directoryInfoList);

        return secondaryFileStructure;
    }

    /* 复制文件内容 */
    public void copyFile(String oldPath, String newPath){
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ( (byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                   // System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        }
        catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();

        }

    }

    @Override /* 复制文件内容 */
    public void copyFile(File source, File dest) {
        try {
            FileUtils.copyFile(source, dest);
        } catch (Exception e) {
        }
    }

    @Override/*复制整个文件夹内容*/
    public void copyFolder(String oldPath, String newPath) {

        try {
            (new File(newPath)).mkdirs(); //如果文件夹不存在 则建立新文件夹
            File a=new File(oldPath);
            String[] file=a.list();
            File temp=null;
            for (int i = 0; i < file.length; i++) {
                if(oldPath.endsWith(File.separator)){
                    temp=new File(oldPath+file[i]);
                }
                else{
                    temp=new File(oldPath+File.separator+file[i]);
                }
                if(temp.isFile()){
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath + "/" +
                            (temp.getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ( (len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if(temp.isDirectory()){//如果是子文件夹
                    copyFolder(oldPath+"/"+file[i],newPath+"/"+file[i]);
                }
            }
        }
        catch (Exception e) {
            System.out.println("复制整个文件夹内容操作出错");
            e.printStackTrace();

        }

    }

    @Override/**/
    public List<String> getClientNameList(String clientName){

        return pdmProductInfoMapper.selectClientNameList(clientName);
    }

    @Override/**/
    public List<String> getDeliverNameList(String deliverName){

        return pdmProductInfoMapper.selectDeliverNameList(deliverName);
    }

    @Override/**/
    public List<String> getProducerList(String producer){

        return pdmProducerInfoMapper.selectProducerList(producer);
    }

    @Override/**/
    public int updateProductInfoForTheme(PdmProductInfo pdmProductInfo){
       return pdmProductInfoMapper.insert(pdmProductInfo);
    }

    @Override/**/
    public String getThemeticProductName(String tempId){

        return pdmArchiveCheckInfoMapper.selectFileNameById(tempId);
    }

    @Override/**/
    public String getThemeticProductTemporaryPath(String tempId){

        return pdmArchiveCheckInfoMapper.selectTemporaryPathById(tempId);
    }

    @Override/**/
    public int updateThemeticProductDetailIndustry(PdmThemeticProductDetailIndustryInfo pdmThemeticProductDetailIndustryInfo){

        return pdmThemeticProductDetailIndustryInfoMapper.insert(pdmThemeticProductDetailIndustryInfo);
    }

    @Override/**/
    public int updateThemeticProductDetail(PdmThemeticProductDetailInfo pdmThemeticProductDetailInfo){

        return pdmThemeticProductDetailInfoMapper.insert(pdmThemeticProductDetailInfo);
    }

    @Override/**/
    public int updateThemeticProduct(PdmThemeticProductInfo pdmThemeticProductInfo){

        return pdmThemeticProductInfoMapper.insert(pdmThemeticProductInfo);
    }

    @Override
    public int insertPdmProducerInfo(String producerName){

        PdmProducerInfo pdmProducerInfo = new PdmProducerInfo();
        pdmProducerInfo.setProducer(producerName);
        return pdmProducerInfoMapper.insert(pdmProducerInfo);
    }

    @Override
    public int selectCountByProducerName(String producerName){

        return pdmProducerInfoMapper.selectCountByProducerName(producerName);
    }

    @Override
    public int updatePdmProducerInfo(String producerName){

        int count = selectCountByProducerName(producerName);
        if (count == 0) {
            insertPdmProducerInfo(producerName);
        }
        return 1;
    }

    @Override
    public List<PdmArchiveRecordsInfo> getArchiveRecordList(String archivePersonnel){

        return pdmArchiveRecordsInfoMapper.selectArchiveRecordsByArchivePersonnel(archivePersonnel);
    }

    @Override
    public int updateArchiveRecordsInfo(PdmArchiveRecordsInfo pdmArchiveRecordsInfo,String tempId){

        pdmArchiveRecordsInfoMapper.updateArchiveRecordsInfo(pdmArchiveRecordsInfo.getProductId(),pdmArchiveRecordsInfo.getArchiveResult(),tempId);
        return 1;
    }

    @Override
    public String getArchivePersonnelName(String archivePersonnel){

        return pdmUserInfoMapper.selectUserNameByUserId(archivePersonnel);
    }
}
