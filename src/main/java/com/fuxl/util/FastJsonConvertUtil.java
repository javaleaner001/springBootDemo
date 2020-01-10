package com.fuxl.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * FastJson
 */
public class FastJsonConvertUtil {

    /**
     * convertJSONToObject
     *
     * @param data
     * @param clzss
     * @param <T>
     * @return
     */
    public static <T> T convertJSONToObject(String data, Class<T> clzss) {
        T t = JSON.parseObject(data, clzss);
        return t;
    }

    /**
     * convertJSONToObject
     *
     * @param data
     * @param clzss
     * @param <T>
     * @return
     */
    public static <T> T convertJSONToObject(JSONObject data, Class<T> clzss) {
        T t = JSON.toJavaObject(data, clzss);
        return t;
    }

    /**
     * convertJSONToArray
     *
     * @param data
     * @param clzss
     * @param <T>
     * @return
     */
    public static <T> List<T> convertJSONToArray(String data, Class<T> clzss) {
        List<T> ts = JSON.parseArray(data, clzss);
        return ts;
    }

    /**
     * convertJSONToArray
     *
     * @param jsonObjects
     * @param clzss
     * @param <T>
     * @return
     */
    public static <T> List<T> convertJSONToArray(List<JSONObject> jsonObjects, Class<T> clzss) {
        List<T> t = new ArrayList<T>();
        for (JSONObject jsonObject : jsonObjects) {
            t.add(convertJSONToObject(jsonObject, clzss));
        }
        return t;
    }

    /**
     *
     * @param object
     * @return
     */
    public static String convertObjectToJson(Object object){
        return JSON.toJSONString(object);
    }

    /**
     *
     * @param object
     * @return
     */
    public static JSONObject convertObjectToJsonObject(Object object){
        return (JSONObject) JSONObject.toJSON(object);
    }
}
