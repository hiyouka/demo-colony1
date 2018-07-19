package com.jy.utils;

import org.springframework.beans.factory.annotation.Value;

/**
 * 〈〉
 *
 * @author jianglei
 * @create 2018/6/27
 * @since 1.0.0
 */
//极光推送工具类 demo
public class JPushUtil {

    @Value("appKey")
    private String appKeys;

    @Value("masterSecret")
    private String masterSecret;

//    private static JPushClient


    public static void sendAllMessage(String message){

    }

}