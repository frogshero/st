package com.tools.st;

import com.tools.st.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RedisTest {
    @Autowired
    private RedisService redisService;

    @Test
    public void set() {
        boolean ret = redisService.setIfAbsent("test121", "vv", 10);
        log.info("{}", ret);

        ret = redisService.setIfAbsent("test121", "vv", 10);
        log.info("{}", ret);

//        ret = redisService.opsForValue().setIfAbsent("test121", "vv", 10, TimeUnit.SECONDS);
//        log.info("{}", ret);
    }

}
