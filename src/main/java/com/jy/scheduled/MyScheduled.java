package com.jy.scheduled;

import com.jy.websocket.WebSocketDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jianglei
 * @create 2018/6/8
 * @since 1.0.0
 */
@Component
public class MyScheduled {




    @Autowired
    private MessageCache messageCache;


    /*没过三秒执行一次*/
    @Scheduled(cron = "0/3 * * * * ?")
    public void run(){

        ConcurrentHashMap<String, WebSocketDemo> wsClientMap = WebSocketDemo.wsClientMap;
        try {
            for ( WebSocketDemo item : wsClientMap.values()){
//                item.sendMessage(dcsLineItem.getItemVal());
                String message = messageCache.getValue("冷却机F1A#2风机进口阀门反馈");
                if (item != null && !StringUtils.isEmpty(message)){
                    System.out.println(item);
                    item.sendMessage(message);
                }
            }
    //      System.out.println("成功群送一条消息:" + wsClientMap.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}