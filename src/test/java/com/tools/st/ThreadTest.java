package com.tools.st;

import lombok.Data;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.metrics.stats.Count;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.concurrent.*;

@Slf4j
public class ThreadTest {

    @Data
    static class MyThread extends Thread {
        private volatile boolean go = true;
        private CountDownLatch cdl;
        public MyThread(CountDownLatch cdl) {
            this.cdl = cdl;
        }
        @Override
        public void run() {
            try {
                while (go) {
                    log.info(Thread.currentThread().getName());
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                this.cdl.countDown();
            }
        }
    }

    @Data
    static class MyFuture implements Callable<String> {
        private volatile boolean go = true;
        @Override
        public String call() {
            while (go) {
                log.info(Thread.currentThread().getName());
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "xxx";
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        testFuture();
    }

    public static void testFuture() throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();
        MyFuture mf = new MyFuture();
        FutureTask<String> ft = new FutureTask<String>(mf);
        Future<?> ff = es.submit(ft);

        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mf.setGo(false);
        }).start();

//        ft.get();
        ff.get();
        es.shutdown();
    }

    public void testLdt() throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(1);
        MyThread mt = new MyThread(cdl);
        mt.start();
        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mt.setGo(false);
        }).start();

        cdl.await();
    }
}
