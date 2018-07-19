package com.jy.scheduled;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈〉
 *
 * @author jianglei
 * @create 2018/6/13
 * @since 1.0.0
 */
@Component
public class MessageCache {
    private static  Map<String,String> map = new HashMap<>();

    public void setValue(String key,String value){
        map.put(key,value);
    }

    public String getValue(String key){
        return map.get(key);
    }
}