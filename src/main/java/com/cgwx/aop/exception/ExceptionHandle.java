package com.cgwx.aop.exception;//package com.cgwx.aop.exception;
//
//import com.cgwx.aop.aspect.HttpAspect;
//import com.cgwx.aop.result.Result;
//import com.cgwx.aop.result.ResultUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@ControllerAdvice
//public class ExceptionHandle {
//
//    /**
//     * 判断错误是否是已定义的已知错误，不是则由未知错误代替，同时记录在log中
//     *
//     * @param e
//     * @return
//     */
//    private final static Logger LOGGER = LoggerFactory.getLogger(HttpAspect.class);
//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public Result exceptionGet(Exception e) {
//        if (e instanceof DescribeException) {
//            DescribeException MyException = (DescribeException) e;
//            return ResultUtil.error(MyException.getCode(), MyException.getMessage());
//        }
//
//        LOGGER.error("【系统异常】{}", e);
//        return ResultUtil.error(ExceptionEnum.UNKNOWN_ERROR);
//    }
//}
