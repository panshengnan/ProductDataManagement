package com.cgwx.common.utils;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by Sun on 2017/9/7.
 */
public class MyFileUtils {

    private static Logger logger = Logger.getLogger(MyFileUtils.class);

    /**
     * 获取文件物理路径
     * @param virtualPath
     * @param address
     * @param virtualBasePath
     * @param physicalBasePath
     * @return
     */
    public static String getPhysicalPath(String virtualPath, String address, String virtualBasePath, String physicalBasePath) {
        return virtualPath.replace("http://" + address + virtualBasePath, physicalBasePath);
    }

    /**
     * 文件上传
     * @param multipartFile 文件对象
     * @param filePhysicalBase  文件夹物理路径
     * @param fileVirtualBase   文件夹虚拟路径
     * @param address    系统IP地址（包含IP，端口号，项目名）
     * @return
     */
    public static String saveFile(MultipartFile multipartFile, String fileVirtualBase, String filePhysicalBase, String address) {
        String filePath = "";
        if (!CommonUtils.isEmpty(multipartFile)) {
            String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
            String filename = Generator.getFileName(Generator.getId(), suffix);
            String filePhysicalPath = filePhysicalBase + filename;
            File file = new File(filePhysicalBase);
            FileOutputStream out = null;
            try {
                if (!file.exists()) {
                    file.mkdirs();
                }
                out = new FileOutputStream(filePhysicalPath);
                out.write(multipartFile.getBytes());
                out.flush();
                filePath = "http://" + address + fileVirtualBase + filename;
                logger.info(filePath + " --file upload success");
            } catch (IOException e) {
                logger.info("file upload failure");
                logger.error(e);
            } finally {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.info("stream close failure");
                    logger.error(e);
                }
            }
        }
        return filePath;
    }

    /**
     * 复制文件
     * @param sourceVirtualPath   原文件虚拟路径
     * @param sourcePhysicalPath  原文件物理路径
     * @param destFileName   新文件名称
     * @return
     */
    public static String copyFile(String sourceVirtualPath, String sourcePhysicalPath, String destFileName){
        File sourceFile = new File(sourcePhysicalPath);
        File destFile = new File(sourcePhysicalPath.replace(sourceFile.getName().substring(0, sourceFile.getName().lastIndexOf(".")), destFileName));
        String destFilePath = "";
        try {
            Files.copy(sourceFile.toPath(), destFile.toPath());
            destFilePath = sourceVirtualPath.replace(sourceFile.getName().substring(0, sourceFile.getName().lastIndexOf(".")), destFileName);
            logger.info("file copy success -- " + sourceVirtualPath + " to " + destFilePath);
        } catch (IOException e) {
            logger.info("file copy failure");
            logger.error(e);
        }
        return destFilePath;
    }

    /**
     * 修改文件名称
     * @param originalPhysicalPath  文件物理路径
     * @param originalVirtualPath   文件虚拟路径
     * @param newName   新文件名
     * @return
     */
    public static String renameFile(String originalVirtualPath, String originalPhysicalPath, String newName) {
        File file = new File(originalPhysicalPath);
        String newPath = originalVirtualPath.replace(originalVirtualPath.substring(originalVirtualPath.lastIndexOf("/") + 1, originalVirtualPath.lastIndexOf(".")), newName);
        newName = originalPhysicalPath.replace(file.getName().substring(0, file.getName().lastIndexOf(".")), newName);
        file.renameTo(new File(newName));
        logger.info(originalVirtualPath + " --rename to-- " + newPath);
        return newPath;
    }
}
