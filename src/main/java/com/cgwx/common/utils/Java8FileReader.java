package com.cgwx.common.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;

/**
 * @author wanghao
 * @create 2017-11-01 下午 17:59
 **/

public class Java8FileReader {
    public static void main(String[] args) throws IOException {
        String file = new String(readAllBytes(get("d:/10yes.json")),  Charset.forName("gbk"));
        Log4jKit.info(file);
    }
}
