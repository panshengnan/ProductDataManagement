package com.cgwx.common.utils;

/**
 * Created by Administrator on 2017/11/17.
 */
public class PublishLayer {
    public static String GetCrsEPSG(String crs) {

        String[] par = crs.split("AUTHORITY");
        String[] string = par[par.length - 1].split("\\[\\\"|\\\",\\\"|\\\"]]");
        return string[2];
    }
}
