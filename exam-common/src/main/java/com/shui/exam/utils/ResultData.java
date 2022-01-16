package com.shui.exam.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResultData {

    private Boolean success;

    private Integer code;


    private String message;

    private Map<String, Object> data = new HashMap<String, Object>();

    //把构造方法私有

    private ResultData() {
    }

    //成功的静态方法
    public static ResultData success() {
        ResultData resultData = new ResultData();
        resultData.setCode(ResponseState.SUCCESS.getValue());
        resultData.setSuccess(true);
        resultData.setMessage("成功");
        return resultData;
    }

    //失败的静态方法
    public static ResultData error() {
        ResultData resultData = new ResultData();
        resultData.setCode(ResponseState.ERROR.getValue());
        resultData.setSuccess(false);
        resultData.setMessage("失败");
        return resultData;
    }

    //链式编程
    public ResultData success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public ResultData message(String message) {
        this.setMessage(message);
        return this;
    }

    public ResultData code(Integer code) {
        this.setCode(code);
        return this;
    }

    public ResultData data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public ResultData data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
}
