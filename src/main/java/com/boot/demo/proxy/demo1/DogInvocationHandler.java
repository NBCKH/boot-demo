package com.boot.demo.proxy.demo1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by chenkaihua on 2018-02-08.
 */
public class DogInvocationHandler implements InvocationHandler {
    private Object target;
    public void setTarget(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        DogUtil du = new DogUtil();
        du.method1();
        Object invoke = method.invoke(target, args);
        du.method2();
        return invoke;
    }
}
