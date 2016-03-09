package com.app.mvc.beans;

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

