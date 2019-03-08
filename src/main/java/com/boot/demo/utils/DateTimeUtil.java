package com.boot.demo.utils;

import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
    public static String begin_time = CommonUtil.getProperty("begin_time");
    public static String end_time = CommonUtil.getProperty("end_time");

    public static Boolean isBelong(){
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Date now = null;
        Date beginTime = null;
        Date endTime = null;
        try {
            now = df.parse(df.format(new Date()));
            beginTime = df.parse(begin_time);
            endTime = df.parse(end_time);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Boolean flag = belongCalendar(now, beginTime, endTime);
        // System.out.println(flag);
        return flag;
    }


    /**
     * 判断时间是否在时间段内
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static Boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        //System.out.println(DateTimeUtil.isBelong());

        DateTime dateTime = new DateTime("2017-12-31");
        System.out.println("dateTime=" + dateTime.toString("yyyy-MM-dd"));
        System.out.println("dateTime=" + dateTime.toString("yyyy-MM"));
        DateTime addDateTime = dateTime.plusMonths(1);
        System.out.println("add one month dateTime=" + addDateTime.toString("yyyy-MM"));
        System.out.println("add one month dateTime=" + addDateTime.toString("yyyy-MM-dd"));

        DateTime decDateTime = dateTime.plusMonths(-2);
        System.out.println("decrease one month dateTime=" + decDateTime.toString("yyyy-MM"));
        System.out.println("decrease one month dateTime=" + decDateTime.toString("yyyy-MM-dd"));

        System.out.println("test1 day of month = " + dateTime.getDayOfMonth());
        DateTime time = new DateTime(new Date());
        System.out.println("test2 day of month = " + time.getDayOfMonth());

        double a = 0.01d;
        System.out.println("a = " + a);
        BigDecimal bg = new BigDecimal(a + "");
        System.out.println("a = " + bg);
        System.out.println("a = " + bg.doubleValue());
        System.out.println("a = " + bg.toString());

        BigDecimal bg1 = new BigDecimal("0.01");
        BigDecimal bg2 = new BigDecimal("0.02");
        System.out.println("bg1=0.01,bg2=0.02,bg2.compareTo(bg1)=" + bg2.compareTo(bg1));
        System.out.println("bg1=0.01,bg2=0.02,bg1.compareTo(bg2)=" + bg1.compareTo(bg2));

        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime.toDate());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date date = calendar.getTime();
        String firstDate = dateFormat2.format(date);
        System.err.println(firstDate);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        date = calendar.getTime();
        String lastDate = dateFormat2.format(date);
        System.err.println(lastDate);

        System.out.println(Integer.valueOf("2018-01-05".substring(8)));
        System.out.println("yyyy-MM-dd".substring(0, 6));
        System.out.println("yyyy-MM-dd".substring(0, 7));
        String cardNo = "4033********7009";
        System.out.println("cardNo = " + cardNo.substring(cardNo.length() - 4));

        DateTime dateTime3 = new DateTime(new Date());
        System.out.println("day=" + dateTime3.getDayOfMonth() + ", month=" + dateTime3.getMonthOfYear());

    }
}
