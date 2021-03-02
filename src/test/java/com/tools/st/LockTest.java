package com.tools.st;

import net.javacrumbs.shedlock.core.LockConfiguration;
import net.javacrumbs.shedlock.core.LockingTaskExecutor;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LockTest {
    private ExecutorService threadPool = Executors.newFixedThreadPool(10);
    LockingTaskExecutor executor;

    @Test
    public void testLock() {
        Runnable runnable = () -> System.out.println("OK");
        threadPool.execute(() -> {
            //为每个线程加锁，保证任务同时只有一个
            executor.executeWithLock(runnable, new LockConfiguration("kqSyncData_1234", Instant.now().plusSeconds(3600)));
        });
    }
}
