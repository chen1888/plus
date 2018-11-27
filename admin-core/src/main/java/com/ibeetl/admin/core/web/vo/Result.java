package com.ibeetl.admin.core.web.vo;

/**
 * @Author: chenxi
 * @Date: 2018-11-21 14:31
 * @description:
 **/
public class Result {
    private Integer code;
    private String message;
    private Object data;

    public Result(){
        this.code = 200;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
