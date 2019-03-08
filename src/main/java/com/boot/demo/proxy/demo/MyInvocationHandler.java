package com.boot.demo.proxy.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by chenkaihua on 2018/2/8.
 */
public class MyInvocationHandler implements InvocationHandler{

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("----正在执行的方法：name=" + method.getName() + ", method=" + method);
        if (args != null) {
            System.out.println("下面是执行该方法时传入的实参：");
            for (Object arg : args) {
                System.out.println(arg);
            }
        } else {
            System.out.println("调用该方法没有实参！");
        }
        System.out.println("----方法：name=" + method.getName() + " 执行结束！");
        System.out.println("---------------------------------------------");
        return null;
    }
}
