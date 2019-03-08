package com.boot.demo.proxy.demo1;

/**
 * Created by chenkaihua on 2018-02-08.
 */
public class GunDog implements Dog {
    @Override
    public void info() {
        System.out.println("我是一只猎狗");
    }

    @Override
    public void run() {
        System.out.println("我奔跑迅速");
    }
}
