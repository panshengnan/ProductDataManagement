package com.cgwx.aop.exception;

public enum ExceptionEnum {

    //可以参考    http://www.ruanyifeng.com/blog/2014/05/restful_api.html

    /*      200 OK - [GET]：服务器成功返回用户请求的数据，该操作是幂等的（Idempotent）。
            201 CREATED - [POST/PUT/PATCH]：用户新建或修改数据成功。
            202 Accepted - [*]：表示一个请求已经进入后台排队（异步任务）
            204 NO CONTENT - [DELETE]：用户删除数据成功。
            400 INVALID REQUEST - [POST/PUT/PATCH]：用户发出的请求有错误，服务器没有进行新建或修改数据的操作，该操作是幂等的。
            401 Unauthorized - [*]：表示用户没有权限（令牌、用户名、密码错误）。
            403 Forbidden - [*] 表示用户得到授权（与401错误相对），但是访问是被禁止的。
            404 NOT FOUND - [*]：用户发出的请求针对的是不存在的记录，服务器没有进行操作，该操作是幂等的。
            406 Not Acceptable - [GET]：用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）。
            410 Gone -[GET]：用户请求的资源被永久删除，且不会再得到的。
            422 Unprocesable entity - [POST/PUT/PATCH] 当创建一个对象时，发生一个验证错误。
            500 INTERNAL SERVER ERROR - [*]：服务器发生错误，用户将无法判断发出的请求是否成功。*/
    UNKNOWN_ERROR(-1, "未知错误"),
    PARAM_ILLEGAL(102, "传入的参数不合法"),
    METHOD_RETURN_NULL(103, "方法返回为空"),
    UPLOAD_FAILURE_REASON_EXCEPTION(104, "上传过程出现异常"),
    UPLOAD_FAILURE_REASON_NULL_FILE(105, "传入文件为空"),
    AREA_TOO_LARGE(401, "面积过大！"),
    CITY_PARAM_INVALID(402, "国内城市参数错误！"),
    SUBMIT_FAILED(403, "提交失败！可能原因：插入订单失败或发送短信失败"),
    MESSAGE_SEND_FAILED(404, "云信发送失败！"),
    CAL_AREA_ERROR(405, "计算面积出现错误！"),
    AREA_TOO_SMALL(406, "面积过小！"),
    PARAM_WRONG(501,"参数错误"),
    PARAM_NAME_REPETITION(502,"命名重复"),
    WEBSERVICE_RETURN_ERROR(514,"webservice返回错误"),
    PUBLISH_LAYER_ERROR(511,"图层发布错误"),
    ARCHIVE_PRODUCT_INVALD(503,"归档数据不满足归档规范！"),


    /**
     * qiqi 2017/11/14.
     */
    USER_NOT_EXIST(508, "用户名或密码不正确"),
    NOT_EXIST(509,"不存在"),
    EXIST(504, "已存在"),
    EXIST_USER(505, "存在用户"),



    ARCHIVE_FAILED(521, "已存在的归档"),

    ARCHIVE_IMAGE_FAILED(512, "已存在的图像归档"),
    ARCHIVE_VIDEO_FAILED(513, "已存在的视频归档"),

    FAILED(506, "失败"),
    ARCHIVE_THEMATIC_FAILED(518, "已存在的专题归档"),
    OVER_TIME(507, "超时"),
    JSON_ERROR(531, "JSON format error"),
    FORMAT_ERROR(530, "格式错误");







    private Integer code;

    private String message;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return message;
    }
}