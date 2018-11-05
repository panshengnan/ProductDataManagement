package com.cgwx.common.utils;

import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User:SunTao
 * Date:2017/2/25
 * Time:9:13
 * Email:suntao2808@163.com
 * Description
 */
public class Generator {

  /**
   * 生成UUID
   * @return
   */
  public static String getUUID() {
    String s = UUID.randomUUID().toString();
    return s.replace("-", "");
  }

  /**
   * 根据时间戳生成id
   * @return
   */
  public static String getId() {
    return DateUtils.getCurrentTime(DateUtils.YY_MM_DD_HH_MM_SS_SSS_PATTERN) + Math.round(Math.random() * 9999);
  }

  /**
   * 获取文件完整名称
   * @param fileName  文件名称
   * @param suffix  文件类型
   * @return
   */
  public static String getFileName(String fileName, String suffix) {
    return fileName + "." + suffix;
  }
}
