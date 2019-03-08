package com.boot.demo.utils;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.Date;

/**
 * Created by chenkaihua on 2018-02-07.
 */
public class MyTest2 {

    public static void main(String[] args) {

//        System.out.println(args.length);
//        System.out.println("args=" + args.toString());
//        for (String str : args) {
//            System.out.println(str);
//        }
//        System.out.println("args[0]=" + args[0]);
//        System.out.println("args[1]=" + args[1]);
        Integer day = Days.daysBetween(new DateTime(new Date()),new DateTime("2018-3-23")).getDays();
        System.out.println("".length());


    }
}
