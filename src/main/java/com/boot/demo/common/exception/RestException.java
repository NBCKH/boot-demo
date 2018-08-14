package com.boot.demo.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author chenkaihua
 * @date 2018/8/7 14:40
 * 自定义异常处理类
 */
@Getter
@Setter
public class RestException extends RuntimeException {

    private String msg;
    private int code = 500; //默认500的错

    public RestException(String msg){
        super(msg);
        this.msg = msg;
    }

    public RestException(String msg,Throwable throwable){
        super(msg,throwable);
        this.msg = msg;
    }

    public RestException(String msg,int code){
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public RestException(String msg,int code,Throwable throwable){
        super(msg,throwable);
        this.msg = msg;
        this.code = code;
    }


}
