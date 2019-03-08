package com.boot.demo.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


/**
 * @author chenkaihua
 * @since 2017/12/12 21:17.
 */
public class TestTimeJob {
    public static void main(String args[]) throws Exception
    {
        Temp command = new Temp();
        
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        ScheduledFuture<?> scheduleTask = scheduler.scheduleWithFixedDelay(command, 2, 3, TimeUnit.SECONDS);
    }
}

class Temp extends Thread {
    @Override
    public void run()
    {
        System.out.println("run time:" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
    }
}