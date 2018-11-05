package com.cgwx.common.constants;

import java.math.BigDecimal;

/**
 * 常量
 *
 * @author wanghao
 * @create 2017-09-13 下午 20:38
 **/

public class Constants {
    public static final String SATELLITE_VIDEO_LOCAL_PATH = "http://10.10.90.11:8086/video/";
    public static final String SATELLITE_VIDEO_SERVER_PATH = "";
    public static final String SATELLITE_IMAGE_LOCAL_PATH = "http://10.10.90.11:8086/101A/";
    public static final String SATELLITE_IMAGE_SERVER_PATH = "http://59.110.162.194:8085/ygyg/101A/";
    public static final String NIGHT_TIME_IMAGE_SERVER_PATH = "http://59.110.162.194:8085/ygyg/NIGHT103B/";
    public static final String VIDEO_IMAGE_SERVER_PATH = "http://59.110.162.194:8085/ygyg/VIDEO103B/";
    public static final String THUMB_SUFFIX = "_thumb.jpg";
    public static final String DETAIL_SUFFIX = ".jpg";
    public static final String TARGET_IMAGE_PATH = "E:\\SatelliteData\\101A\\";
    public static final String TARGET_VIDEO_PATH = "E:\\SatelliteData\\video\\";
    public static final String TARGET_VIDEO_XML_PATH = "E:\\videoxml\\";
    public static final String TARGET_VIDEO_AVI_PATH = "G:\\video-product\\avi\\";
    public static final String CONFIG_DATA_FILE_PATH = "file:E:/SatelliteData/";
    public static final BigDecimal IMAGE_PRICE = BigDecimal.valueOf(1000.00);
    public static final BigDecimal VIDEO_PRICE = BigDecimal.valueOf(1000.00);
    public static final Double AREA_CONSTRAINT_MAX_DOUBLE = Double.valueOf("200000000000");
    public static final Double AREA_CONSTRAINT_MIN_DOUBLE = Double.valueOf("200000000");
    public static final int PROVINCE_PARENT_ID = -1;
    public static final String DEFAULT_SHOW_TYPE = "0";
    public static final String DEFAULT_VIDEO_TITLE = "视频标题";

    public static final String SALE_TYPE_1 = "一元券";
    public static final String SALE_DESCRIPTION = "每天中午12:00发放一百张";
    public static final String SALE_TYPE_2 = "全场二折起";
    public static final String PRODUCT_SERVICE = "交货时间2天";
    public static final Double MOSAIC_SCENE_UNIT_ORIGINAL_PRICE = 70.00;
    public static final Double MOSAIC_SCENE_UNIT_SALE_PRICE = 19.00;
    public static final Double MOSAIC_CUSTOM_UNIT_ORIGINAL_PRICE = 140.00;
    public static final Double MOSAIC_CUSTOM_UNIT_SALE_PRICE = 39.00;

    public static final String THUMB_FILE_PATH = "E:\\video\\videojpg\\";
    public static final String JPG_FILE_PATH = "E:\\video\\videojpg\\";
    public static final String XML_FILE_PATH = "E:\\file\\xml\\12\\";
}
