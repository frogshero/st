package com.tools.st.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class ThreadPoolExecutorTest {
    @Test
    public void configs() {
        BlockingQueue queue = new LinkedBlockingDeque(10);
        ExecutorService executorService = new ThreadPoolExecutor(
                2,   //一般并发数
                200,   //最大任务数据
                10,  //空闲线程保留时间
                TimeUnit.MINUTES,
                queue  // 最大100的队列，如果是有界队列，100就是扩容的界限，超过100就新建线程直接执行，增加了并行度
        );
    }
}
