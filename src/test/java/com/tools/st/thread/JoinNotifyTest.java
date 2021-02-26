package com.tools.st.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class JoinNotifyTest {

    private static Object obj1 = new Object();
    @Test
    public void test() throws InterruptedException {
        //必须拥有监控器才能wait，表示放弃监控器
        CountDownLatch cdl = new CountDownLatch(2);
        new Thread(() -> {
            synchronized (obj1) {
                try {
                    System.out.println("先进入-----");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("wait-----");
                    obj1.wait();
                    System.out.println("被唤醒-----");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            cdl.countDown();
            System.out.println(cdl.getCount());
        }).start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("等待先进入的线程放弃锁-----");
            synchronized (obj1) {
                //拥有锁才能notify
                System.out.println("notify-----");
                obj1.notifyAll();  //IllegalMonitorStateException
            }
            cdl.countDown();
            System.out.println(cdl.getCount());
        }).start();

        cdl.await();
    }
}
