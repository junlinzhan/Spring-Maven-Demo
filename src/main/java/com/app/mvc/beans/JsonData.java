package com.app.mvc.beans;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.Map;

public class JsonData {

    private final boolean ret;
    private String msg;
    private Object data;

    private JsonData(boolean ret) {
        this.ret = ret;
    }

    public static JsonData error(String message) {
        JsonData result = new JsonData(false);
        result.msg = message;
        return result;
    }

    public static JsonData success(Object object, String msg) {
        JsonData result = new JsonData(true);
        result.data = object;
        result.msg = msg;
        return result;
    }

    public static JsonData success(Object object) {
        return success(object, null);
    }

    public static JsonData success() {
        return success(null, null);
    }

    public static <T> JsonData list(Collection<T> content, Integer totalRows, String msg) {
        Preconditions.checkNotNull(content);  // fast fail
        JsonData result = new JsonData(true);
        Map<String, Object> map = Maps.newHashMap();
        map.put("content", content);
        if (totalRows == null) {
            map.put("totalRows", content.size());
        } else {
            map.put("totalRows", totalRows);
        }
        result.msg = msg;
        result.data = map;
        return result;
    }

    public static <T> JsonData list(Collection<T> content, String msg) {
        return list(content, null, msg);
    }

    public static <T> JsonData list(Collection<T> content) {
        return list(content, null, null);
    }

    public static <T> JsonData list(Collection<T> content, Integer totalRows) {
        return list(content, totalRows, null);
    }

    public boolean getRet() {
        return ret;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

}

