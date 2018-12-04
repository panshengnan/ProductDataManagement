package com.cgwx.service;

import com.cgwx.data.entity.PdmProductLayerInfo;
import net.sf.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "publishOrder")
public class ReceiverForPublishOrder {

    @Autowired
    LayerPublishService layerPublishService;

    @RabbitHandler
    public void receiver(JSONObject msg) {

        try {
            System.out.println("路径是：" + msg.get("path"));
            JSONObject jsonObject = layerPublishService.publishTif(msg.getString("path"), "tifPublishTest", "#000000");
            PdmProductLayerInfo pdmProductLayerInfo = new PdmProductLayerInfo();
            pdmProductLayerInfo.setProductId(msg.getString("productId"));
            pdmProductLayerInfo.setSingleId(msg.getString("singleId"));
            pdmProductLayerInfo.setLayerName("tifPublishTest:" + jsonObject.getString("fileName"));
            layerPublishService.updateProductLayerInfo(pdmProductLayerInfo);
            if (Integer.parseInt(msg.getString("productType")) == 1) {
                layerPublishService.updateThemeticProductDetailImgGeo(msg.getString("productId"), msg.getString("singleId"), jsonObject.getString("geoJson"));
            }
        } catch (Exception e) {
            System.out.println("图像发布过程存在异常！");
        }
    }

}
