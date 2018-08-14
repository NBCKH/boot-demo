package com.boot.demo.common.exception;

import com.boot.demo.common.component.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author chenkaihua
 * @date 2018/8/7 14:41
 * 全局异常处理器
 */
@RestControllerAdvice   //全局异常处理注解
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(RestException.class)   //表示处理哪种异常
    public Result handleRestxception(RestException e){
        Result result = new Result();
        result.put("code",e.getCode());
        result.put("msg",e.getMsg());
        return result;
    }



    //处理最大的异常
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        log.error(e.getMessage(),e);
        return Result.error();
    }
}
