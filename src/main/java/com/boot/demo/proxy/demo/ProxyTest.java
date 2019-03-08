package com.boot.demo.proxy.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by chenkaihua on 2018/2/8.
 */
public class ProxyTest {

    public static void main(String[] args) throws Exception{
        InvocationHandler handler = new MyInvocationHandler();
        Person p = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[] {Person.class}, handler);
        p.walk();
        p.sayHello("齐天大圣");
    }
}
