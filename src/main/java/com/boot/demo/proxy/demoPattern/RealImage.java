package com.boot.demo.proxy.demoPattern;

/**
 * @author chenkaihua
 * @date 2018/5/19 14:47
 */
public class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName){
        this.fileName = fileName;
        loadFromRealImage(fileName);
    }

    @Override
    public void dispaly() {
        System.out.println("接口重载方法里realImage:"+ fileName);
    }

    private void loadFromRealImage(String fileName){
        System.out.println("方法里realImage:"+ fileName);
    }
}
