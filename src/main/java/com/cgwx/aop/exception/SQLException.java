package com.cgwx.aop.exception;

/**
 * Created by Kenzi on 2017/11/16.
 */
public class SQLException  extends RuntimeException {
    private Integer code;
    public SQLException(String message, Integer code) {
        super(message);
        this.code = code;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
