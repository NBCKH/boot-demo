package com.boot.demo.proxy.demoPattern;

/**
 * @author chenkaihua
 * @date 2018/5/19 14:52
 */
public class ProxyImage implements Image{

    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void dispaly() {
        if(realImage == null){
            realImage = new RealImage(fileName);
        }
        //调用realImage对像中display()方法
        realImage.dispaly();
    }
}
