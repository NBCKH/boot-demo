package com.boot.demo.proxy.demo1;

import java.lang.reflect.Proxy;

/**
 * Created by chenkaihua on 2018-02-08.
 */
public class MyProxyFactory {
    public static Object getProxy(Object target) {
        DogInvocationHandler handler = new DogInvocationHandler();
        handler.setTarget(target);
        Object instance = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
        return instance;
    }
}
