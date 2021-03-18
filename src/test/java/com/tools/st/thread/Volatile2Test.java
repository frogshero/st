package com.tools.st.thread;

import java.util.concurrent.CountDownLatch;

public class Volatile2Test {
    private volatile boolean flag = true;
    private static CountDownLatch ctl = new CountDownLatch(1);  //必须是static

    public void load() {
        int i = 0;
        while (flag) {
            i++;
        }
        System.out.println(Thread.currentThread().getName() + "Load END");
        ctl.countDown();
        System.out.println(ctl.getCount() + " COUNT");
    }

    public void change() {
        this.flag = false;
//        System.out.println(Thread.currentThread().getName() + "Change END");
    }

}
