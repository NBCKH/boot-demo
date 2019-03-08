package com.boot.demo.proxy.demo1;

/**
 * Created by chenkaihua on 2018-02-08.
 */
public class DogTest {
    public static void main(String[] args) {
        Dog target = new GunDog();
        Dog proxy = (Dog) MyProxyFactory.getProxy(target);
        proxy.info();
        System.out.println("================分割线=====================");
        proxy.run();
    }
}
