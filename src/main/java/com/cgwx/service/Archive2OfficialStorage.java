package com.cgwx.service;

import net.sf.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.File;

@Component
@RabbitListener(queues = "archive2OfficialStorage")
public class Archive2OfficialStorage {

    @Autowired
    IProductArchiveService iProductArchiveService;

    @RabbitHandler
    public void receiver(JSONObject msg) {
        iProductArchiveService.copyFolder(msg.getString("tempPath"), msg.getString("officialPath"));
        File zipFile = new File(msg.getString("zipFilePath"));
        File zipTargetFile = new File(msg.getString("zipFileTargetPath"));
        iProductArchiveService.copyFile(zipFile, zipTargetFile);
    }
}
