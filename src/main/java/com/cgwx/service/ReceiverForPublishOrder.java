package com.cgwx.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "publishOrder")
public class ReceiverForPublishOrder {

//    @RabbitHandler
//    public void receiver(String msg) {
//        int max = 5000;
//        int min = 1000;
//        Random random = new Random();
//        int s = random.nextInt(max) % (max - min + 1) + min;
//        try {
//            Thread.currentThread().sleep(s);//毫秒
//        } catch (Exception e) {
//        }
//        System.out.println("啊是已被处理！" + msg);
//    }

}
