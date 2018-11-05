package com.cgwx.common.utils;

import com.cgwx.common.constants.Constants;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 列出目录下所有的文件
 *
 * @author wanghao
 * @create 2017-09-13 下午 19:20
 **/

public class ListFilesUtil {
    private static final Logger logger = Logger.getLogger(ListFilesUtil.class);

    private static List<File> xmlList = new ArrayList<File>();

    private static int fileCount = 0;
    public static void list(File file) {
        if (file.isDirectory())//判断file是否是目录
        {
            File[] lists = file.listFiles();
            if (lists != null) {
                for (int i = 0; i < lists.length; i++) {
                    list(lists[i]);//是目录就递归进入目录内再进行判断
                }
            }
        }
        //file不是目录，就输出它的路径名，这是递归的出口
        if (file.getName().endsWith("meta.xml")) {
           // copyFile(file.getAbsolutePath(), Constants.TARGET_VIDEO_AVI_PATH);
            xmlList.add(file);
        }
/*
        else if (file.getName().endsWith(".jpg")) {
            copyFile(file.getAbsolutePath(), Constants.JPG_FILE_PATH);
        } else if (file.getName().endsWith("_thumb.jpg")) {
            String source = file.getAbsolutePath();
            copyFile(source, Constants.THUMB_FILE_PATH);
        }*/
        //System.out.println(file);
    }

    public static List<File> listFiles(String path) {
        File file = new File(path);
        list(file);
        //System.out.println(xmlList);   //此处有一个问题，xmlList变量为static，加入记录后不会清空，下次再加入，原有记录还在，导致插入数据库记录出现冗余
        return xmlList;
    }

//    public static void main(String[] args) {
//        String path = "G:\\101A\\2017\\05\\01";
//        File file = new File(path);
//        list(file);
//        System.out.println(xmlList);
//    }

    private static void copyFile(String sour, String dest) {
        //获取进程
        Runtime run = Runtime.getRuntime();
        Process p = null;
        //得到目标文件名
        String command = "cmd /c copy  " + sour + "  " + dest;
        logger.info(command);
        //执行doc命令
        try {
            p = run.exec(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
