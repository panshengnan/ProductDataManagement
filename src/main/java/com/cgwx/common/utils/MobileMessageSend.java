package com.cgwx.common.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;

public class MobileMessageSend {

    public static String sendMsg(String marketTel, String clientName, String clientTel,
                                 String order1, String order2, String order3) {
        String appKey = "995e5b66603b8289e18f86e2bdbe8f7d";
        String appSecret = "7829a8af8d2c";
        // 随机数（最大长度128个字符）
        String nonce = "herunze";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        // 当前UTC时间戳
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            //网址是网易云通信给的发送通知类短信所提供的的接口
            String url = "https://api.netease.im/sms/sendtemplate.action";
            // url编码；防止不识别中文
            String marketTel1 = URLEncoder.encode(marketTel, "utf-8");
            String clientName1 = URLEncoder.encode(clientName, "utf-8");
            String clientTel1 = URLEncoder.encode(clientTel, "utf-8");
            String params = "templateid=3115002&mobiles=[\"" + marketTel1 + "\"]"
                    + "&params=" + "[\"" + clientName1 + "\",\"" + clientTel1
                    + "\",\"" + order1 + "\",\"" + order2 + "\",\"" + order3 + "\"]";
            Log4jKit.info("params：" + params);
            //创建URL对象
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("AppKey", appKey);
            conn.setRequestProperty("CheckSum", checkSum);
            conn.setRequestProperty("CurTime", curTime);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            conn.setRequestProperty("Nonce", nonce);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(params);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            Log4jKit.error("发送 POST 请求出现异常！\n" + e);
            e.printStackTrace();
        }
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}


