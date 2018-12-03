package com.cgwx.service;

import com.cgwx.data.entity.PdmProductLayerInfo;
import net.sf.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RabbitListener(queues = "publishOrder")
public class ReceiverForPublishOrder {

    @Autowired
    LayerPublishService layerPublishService;

    @RabbitHandler
    public void receiver(JSONObject msg) {
//        int max = 5000;
//        int min = 1000;
//        Random random = new Random();
//        int s = random.nextInt(max) % (max - min + 1) + min;
//        try {
//            Thread.currentThread().sleep(s);//毫秒
//        } catch (Exception e) {
//        }
//        System.out.println("啊是已被处理！" + msg);
        try {
            System.out.println("路径是："+msg.get("path"));
            JSONObject jsonObject = layerPublishService.publishTif(msg.getString("path"), "tifPublishTest", "#000000");
            PdmProductLayerInfo pdmProductLayerInfo = new PdmProductLayerInfo();
            pdmProductLayerInfo.setProductId(msg.getString("productId"));
            pdmProductLayerInfo.setSingleId(msg.getString("singleId"));
            pdmProductLayerInfo.setLayerName("tifPublishTest:"+jsonObject.getString("fileName"));
            layerPublishService.updateProductLayerInfo(pdmProductLayerInfo);
            if(Integer.parseInt(msg.getString("productType")) == 1)
            {
                layerPublishService.updateThemeticProductDetailImgGeo(msg.getString("productId"),msg.getString("singleId"),jsonObject.getString("geoJson"));
            }
        } catch (Exception e) {
            System.out.println("存在异常！");
        }
    }

}
