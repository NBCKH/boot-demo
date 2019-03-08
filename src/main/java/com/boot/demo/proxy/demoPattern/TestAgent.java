package com.boot.demo.proxy.demoPattern;

/**
 * @author chenkaihua
 * @date 2018/5/19 14:57
 * 代理模式简单实现
 */
public class TestAgent {
    public static void main(String[] args){
        Image image = new ProxyImage("kaka");
        image.dispaly();
        System.out.println("");
        //对象已经存在
        image.dispaly();


    }
}
