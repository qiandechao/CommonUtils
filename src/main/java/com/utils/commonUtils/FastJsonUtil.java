package com.utils.commonUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/13.
 */
public class FastJsonUtil {
    @SuppressWarnings("unchecked")
    public static Map<String,Object> json2Map(String json){
        return JSON.parseObject(json, Map.class);
    }
    public static Map<String,String> json2StringMap(String json){
        return JSON.parseObject(json, Map.class);
    }
    public static Map<String,Object> json2StringMap2(String json){
        return JSON.parseObject(json, Map.class);
    }
    public static <T> T obj2Object(String obj,Class<T> c){
        return JSON.parseObject(obj,c);
    }

    public static String obj2JsonString(Object obj){
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        return JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat);
    }
    public static List obj2JsonList(String json){
        return JSONObject.parseArray(json);
    }
    public static JSONObject obj2JsonObject(Object object){
        return (JSONObject)JSON.toJSON(object);
    }
}
