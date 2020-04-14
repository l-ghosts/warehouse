package com.lgc.flume.interceptor;

import org.apache.commons.lang.math.NumberUtils;

/**
 * 这是一个flume的日志过滤工具类
 */
public class logUtils {
    public static boolean validateStart(String log) {
        if(log==null){
            return false;
        }

//        校验json文件
        if(!log.trim().startsWith("{")||!log.trim().endsWith("}")){
            return false;
        }

        return true;
    }

    public static boolean validateEvent(String log) {
//        数据
//        服务器时间|json
//        1585042329876|{"cm":{"ln":"-83.4","sv":"V2.5.2","os":"8.1.8",
//        "g":"973700GI@gmail.com","mid":"899","nw":"4G","l":"es","vc":"18",
//        "hw":"1080*1920","ar":"MX","uid":"899","t":"1584953665196",
//        "la":"14.9","md":"sumsung-0","vn":"1.3.3","ba":"Sumsung","sr":"C"},
//        "ap":"app","et":[{"ett":"1584999552771","en":"newsdetail",
//        "kv":{"entry":"2","goodsid":"240","news_staytime":"0","loading_time":"0","action":"3","showtype":"1","category":"100","type1":""}},
//        {"ett":"1585027029847","en":"active_foreground","kv":{"access":"","push_id":"2"}}]}

//        1.切割
        String[] logContent = log.split("\\|");
//        2.校验
        if(logContent.length!=2){
            return false;
        }
//        3.校验服务器的时间  长度和是否数字
        if(logContent[0].length()!=13 || !NumberUtils.isDigits(logContent[0])){
            return false;
        }

        return true;

    }
}
