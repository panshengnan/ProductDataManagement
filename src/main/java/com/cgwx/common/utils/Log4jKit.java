package com.cgwx.common.utils;

import org.apache.log4j.Logger;

/**
 * log4j日志工具类,方便直接输出日志信息，不用每个类都声明logger日志对象
 *
 * @author Administrator
 */
public class Log4jKit {

    /**
     * @method findCaller
     * @description 获取最原始被调用的堆栈信息
     * @param
     * @return java.lang.StackTraceElement
     * @date: 2017年09月12日 09:31:05
     * @author: wanghao
     */
    private static StackTraceElement findCaller() {
        // 获取堆栈信息
        StackTraceElement[] callStack = Thread.currentThread().getStackTrace();
        if (null == callStack) {
            return null;
        }
        // 最原始被调用的堆栈信息
        StackTraceElement caller = null;
        // 日志类名称
        String logClassName = Log4jKit.class.getName();
        // 循环遍历到日志类标识
        boolean isEachLogClass = false;

        // 遍历堆栈信息，获取出最原始被调用的方法信息
        for (StackTraceElement strackTraceEle : callStack) {
            // 遍历到日志类
            if (logClassName.equals(strackTraceEle.getClassName())) {
                isEachLogClass = true;
            }
            // 下一个非日志类的堆栈，就是最原始被调用的方法
            if (isEachLogClass) {
                if (!logClassName.equals(strackTraceEle.getClassName())) {
                    isEachLogClass = false;
                    caller = strackTraceEle;
                    break;
                }
            }
        }
        return caller;
    }

    /**
     * @method logger
     * @description 自动匹配请求类名，生成logger对象，此处 logger name 值为 [className].[methodName]() Line: [fileLine]
     * @param
     * @return org.apache.log4j.Logger
     * @date: 2017年09月12日 09:32:24
     * @author:wanghao
     */
    private static Logger logger() {
        StackTraceElement caller = findCaller();//最原始被调用的堆栈对象
        if (caller == null) {
            return Logger.getLogger(Log4jKit.class);
        } else {
            return Logger.getLogger(caller.getClassName() + "." + caller.getMethodName() + "() Line: " + caller.getLineNumber());
        }
    }

    public static void trace(String msg) {
        trace(msg, null);
    }

    public static void trace(String msg, Throwable e) {
        logger().trace(msg, e);
    }

    public static void debug(String msg) {
        debug(msg, null);
    }

    public static void debug(String msg, Throwable e) {
        logger().debug(msg, e);
    }

    public static void info(String msg) {
        info(msg, null);
    }

    public static void info(String msg, Throwable e) {
        logger().info(msg, e);
    }

    public static void warn(String msg) {
        warn(msg, null);
    }

    public static void warn(String msg, Throwable e) {
        logger().warn(msg, e);
    }

    public static void error(String msg) {
        error(msg, null);
    }

    public static void error(String msg, Throwable e) {
        logger().error(msg, e);
    }

}
