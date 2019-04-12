package com.defence.nevermore.domain;

/**
 * @ProjectName: nevermore
 * @Package: com.defence.nevermore.domain
 * @ClassName: Response
 * @Description: java类作用描述
 * @Author: songxiangfu
 * @CreateDate: 2019/4/12 10:23
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/12 10:23
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Response {

    private static final int successCode = 200;
    private static final int errorCode = 500;
    private static final String successMsg = "ok";
    private static final String errorMsg = "error";

    private int code;

    private String msg;

    private Object obj;

    public Response() {

    }

    public Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Response(int code, String msg, Object obj) {
        this.code = code;
        this.msg = msg;
        this.obj = obj;
    }

    public static Response success(String msg,Object obj) {
        return new Response(successCode, msg,obj);
    }

//    public static Response success(String msg) {
//        return new Response(successCode, msg);
//    }
    public static Response success(Object obj) {
        return new Response(successCode, successMsg,obj);
    }
    public static Response success() {
        return new Response(successCode,successMsg);
    }

    public static Response error(String msg,Object obj) {
        return new Response(errorCode, msg,obj);
    }

    public static Response error(String msg) {
        return new Response(errorCode, msg);
    }
    public static Response error(Object obj) {
        return new Response(errorCode, errorMsg,obj);
    }
    public static Response error() {
        return new Response(errorCode, errorMsg);
    }

    public static Response bind(int code, String msg, Object obj) {
        return new Response(code, msg, obj);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
