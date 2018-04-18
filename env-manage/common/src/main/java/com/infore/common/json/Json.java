package com.infore.common.json;

import com.alibaba.fastjson.JSON;

public class Json {
    /**
     * Convert target object to json string.
     *
     * @param obj target object.
     * @return converted json string.
     */
    public static String getJsonFromObject(Object obj) {
        return JSON.toJSONString(obj);

    }

    /**
     * Convert json string to target object.
     *
     * @param json      json string.
     * @param valueType target object class type.
     * @param <T>       target class type.
     * @return converted target object.
     */
    public static <T> T getObjectFromJson(String json, Class<T> valueType) {
        if (json == null) {
            throw new NullPointerException("Param json is null.");
        }

        if (valueType == null) {
            throw new NullPointerException("Param valueType is null.");
        }

        return JSON.parseObject(json, valueType);

    }

}
