package com.cgwx.common.utils;

import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User:SunTao
 * Date:2017/2/25
 * Time:8:32
 * Email:suntao2808@163.com
 * Description
 */
public class CommonUtils {

  private static Logger logger = Logger.getLogger(CommonUtils.class);

  /**
   * 判断对象是否Empty（null或元素长度为0）
   *
   * @param pObj 待检测对象
   * @return boolean 返回的布尔值
   */
  public static boolean isEmpty(Object pObj) {
    if (pObj == null) {
      return true;
    }
    if (pObj == "") {
      return true;
    }
    if (pObj instanceof String) {
      if (((String) pObj).length() == 0) {
        return true;
      }
    } else if (pObj instanceof Collection) {
      if (((Collection) pObj).size() == 0) {
        return true;
      }
    } else if (pObj instanceof Map) {
      if (((Map) pObj).size() == 0) {
        return true;
      }
    }
    return false;
  }
}
