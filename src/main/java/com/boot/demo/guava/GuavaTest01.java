package com.boot.demo.guava;


import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Created by chenkaihua on 2019-05-22 10:35
 */
public class GuavaTest01 {

    @Test
    public void testList(){

        /**
         * 集合处理
         */
        //静态方法创建list集合
        ArrayList<String> newArray = Lists.newArrayList();

        //将集合按照指定大小拆分小集合
        ArrayList<String> strings = Lists.newArrayListWithCapacity(100 * 1000);
        List<List<String>> partition = Lists.partition(strings, 1000);

        //将集合数据类型转化成其他类型的集合 ---> function 方法可以执行转化动作，将类型 A 的对象 转化为 B 对象
        List<Integer> a = Lists.transform(strings, new Function<String, Integer>() {
            @Override
            public Integer apply(String input) {
                return input.hashCode() + 111;
            }
        });

        //map是属于一个key一个value,但是有的时候是一个key对应了多个value可以使用guava中的Multimap
        ArrayListMultimap<String, Object> multimap = ArrayListMultimap.create();

        /**
         * 字符串处理
         */
        // 实现的连接器将忽略 Null
        Joiner joiner = Joiner.on(";").skipNulls();
        // 字符串为Harry;Ron;Hermione
        joiner.join("Harry", null, "Ron", "Hermione");

        //Splitter字符串拆分器，实现字符串按照指定字符拆分功能
        // 以下拆分将将结果去除首部与尾部空格，以及去除空字符串。所以拆分之后结果为 foo bar qux
        Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split("foo,bar,,   qux");

        /**
         * 时间方法
         */
        //原有java 写法，计算调用时间
        long startTime = System.currentTimeMillis();
        // 业务方法调用

        long endTime = System.currentTimeMillis();

        long totalTime = endTime - startTime;

        //guava 写法
        Stopwatch stopwatch = Stopwatch.createUnstarted();
        // 开始计量时间
        stopwatch.start();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 停止计量时间
        stopwatch.stop();
        // 根据输入时间单位获取相应的时间
        System.out.println(stopwatch.elapsed(TimeUnit.SECONDS));







    }
}
