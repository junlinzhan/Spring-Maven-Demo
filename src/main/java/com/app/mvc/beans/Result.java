package com.app.mvc.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
public class Result {

    private boolean ret;

    private String msg;

    public static Result success(String msg) {
        return new Result(true, msg);
    }

    public static Result fail(String msg) {
        return new Result(false, msg);
    }

    public Result() {
    }

    public Result(boolean ret, String msg) {
        this.ret = ret;
        this.msg = msg;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("ret", ret);
        result.put("msg", msg);
        return result;
    }

    public static Result buildFrom(JsonData jsonData) {
        Result result = new Result();
        result.setRet(jsonData.getRet());
        if (jsonData.getMsg() != null && jsonData.getMsg().length() > 0) {
            result.setMsg(jsonData.getMsg());
        } else {
            result.setMsg(jsonData.getRet() ? "success" : "fail");
        }
        return result;
    }
}
