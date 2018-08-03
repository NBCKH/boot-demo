package com.boot.demo.common.component;


import org.apache.http.HttpStatus;

import java.util.HashMap;

/**
 * 统一返回的结果格式
 * @author chenkaihua
 * @date 2018/8/1 16:06
 */
public class Result extends HashMap<String,Object> {
    private static final long serialVersionUID =1L;

    public Result(){
        put("code",0);
        put("msg","success");
    }

    public static Result ok(){
        return new Result();
    }

    public static Result ok(String msg){
        Result result = new Result();
        result.put("msg",msg);
        return result;
    }

    public static Result error(){
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR,"未知异常,请联系客服");
    }

    public static Result error(String msg){
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR,msg);
    }

    public static Result error(int code,String msg){
        Result result = new Result();
        result.put("code",code);
        result.put("msg",msg);
        return result;
    }

    @Override
    public Result put(String key,Object value){
        super.put(key,value);
        return this;
    }
}
