package com.lgc.flume.interceptor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class LogETLInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
//        获取数据
        byte[] body = event.getBody();
        String log = new String(body, Charset.forName("UTF-8"));

//        判断log类型
        if(log.contains("start")){
            if(logUtils.validateStart(log)){
                return event;
            }
        }else{
            if(logUtils.validateEvent(log)){
                return event;
            }
        }

//        没有就返回空
        return null;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        ArrayList<Event> inteptors = new ArrayList<>();

        for (Event event : events) {
            Event intercept1 = intercept(event);
            if(intercept1 != null){
                inteptors.add(intercept1);
            }
        }
        return inteptors;
    }

    @Override
    public void close() {


    }

    public static class Builder implements Interceptor.Builder{

        @Override
        public Interceptor build() {
            return new LogETLInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
