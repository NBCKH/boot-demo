package com.boot.demo.controller;

import com.boot.demo.common.component.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author chenkaihua
 * @date 2018/8/1 16:03
 */
@Slf4j
public class BaseController {
    //protected 关键字是只有这个类的字类可以访问
    //default即不写修饰符, 那就只有字类, 跟同一个包的类可以访问
    //private只有类本身可以访问


    /**
     * 成功返回格式
     *
     * @return
     */
    protected ResponseEntity success() {
        return new ResponseEntity(Result.ok(), HttpStatus.OK);
    }

    protected ResponseEntity success(String msg) {
        return new ResponseEntity(Result.ok(msg), HttpStatus.OK);
    }

    protected ResponseEntity successObject(Object object) {
        return new ResponseEntity(Result.ok().put("data", object), HttpStatus.OK);
    }

    /**
     * 失败返回格式
     */
    protected ResponseEntity failure() {
        return new ResponseEntity(Result.error(), HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity failure(String msg) {
        return new ResponseEntity(Result.error(msg), HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity failureObject(Object object) {
        return new ResponseEntity(Result.error().put("data", object), HttpStatus.BAD_REQUEST);
    }
}
