package com.tools.st.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class ScheduledTest {

//    @Async("testScheduled5")
//    @Scheduled(cron = "0/5 * * * * ?")   //总是等待上一次执行完毕
//    public void testScheduled5() throws InterruptedException {
//        Thread.sleep(1000);
//    }

//    @Async("testScheduled3")
//    @Scheduled(cron = "0/5 * * * * ?")
//    public void testScheduled3() throws InterruptedException {
//        Thread.sleep(1000);
//    }

    @Qualifier("testExecutor")
    @Autowired
    private ScheduledThreadPoolExecutor testExecutor;

    public void testExe() {
        testExecutor.schedule(() -> System.out.println("xxxxx"),10, TimeUnit.MINUTES);
//
//        testExecutor.schedule(() -> System.out.println("yy2"), 1, TimeUnit.SECONDS);
//        testExecutor.schedule(() -> System.out.println("yy3"), 1, TimeUnit.SECONDS);
//        testExecutor.schedule(() -> System.out.println("yy4"), 1, TimeUnit.SECONDS);
    }

    public void addShort() {
        testExecutor.schedule(() -> System.out.println("yy1"), 61, TimeUnit.SECONDS);
    }

}
