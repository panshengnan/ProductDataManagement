package com.cgwx.aop.result;

import com.cgwx.aop.exception.ExceptionEnum;

public class ResultUtil {

    /**
     * 返回成功，传入返回体具体出參
     *
     * @param object
     * @return
     */
    public static Result success(Object object) {
        Result result = new Result();
        result.setStatus(200);
        result.setMessage("success");
        result.setData(object);
        return result;
    }

//    public static Result successJson(JSONObject object) {
//        Result result = new Result();
//        result.setStatus(200);
//        result.setMessage("success");
//        result.setData(object);
//        return result;
//    }


    /**
     * 提供给部分不需要出參的接口
     *
     * @return
     */
    public static Result success() {
        return success(null);
    }

    /**
     * 自定义错误信息
     *
     * @param code
     * @param message
     * @return
     */
    public static Result error(Integer code, String message) {
        Result result = new Result();
        result.setStatus(code);
        result.setMessage(message);
        result.setData(null);
        return result;
    }

    /**
     * 返回异常信息，在已知的范围内
     *
     * @param exceptionEnum
     * @return
     */
    public static Result error(ExceptionEnum exceptionEnum) {
        Result result = new Result();
        result.setStatus(exceptionEnum.getCode());
        result.setMessage(exceptionEnum.getMsg());
        result.setData(null);
        return result;
    }

    public static Result error(ExceptionEnum exceptionEnum, Object object) {
        Result result = new Result();
        result.setStatus(exceptionEnum.getCode());
        result.setMessage(exceptionEnum.getMsg());
        result.setData(object);
        return result;
    }

}
