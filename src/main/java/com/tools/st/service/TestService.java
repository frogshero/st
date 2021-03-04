package com.tools.st.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Qualifier("test123")
@Slf4j
public class TestService {

    //-默认值不开启
    @Scheduled(cron = "${kq.collect.task.cron:-}")
    public void scheduleTest() {
        log.info("scheduleTest");
    }
}
