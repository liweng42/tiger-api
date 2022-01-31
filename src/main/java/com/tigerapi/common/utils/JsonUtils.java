package com.tigerapi.common.utils;

import com.alibaba.fastjson.JSON;

/**
 * @ClassName JsonUtils
 * @Description
 * @Author jerry
 * @Since 2022/1/30
 */
public class JsonUtils {
    public static String toJSONString(Object object) {
        return JSON.toJSONString(object);
    }
}
