package com.tools.st.service;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

public class MyExecutor extends ScheduledThreadPoolExecutor {
    public MyExecutor(int corePoolSize) {
        super(corePoolSize);
    }
    public MyExecutor(int corePoolSize,
                      ThreadFactory threadFactory) {
        super(corePoolSize, threadFactory);
    }
}
