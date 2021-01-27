package com.tools.st;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;

@Slf4j
public class DateTimeTest {

    @Test
    public void test() {
        log.info("{}", Instant.now().plusSeconds(10).toEpochMilli() - Instant.now().toEpochMilli());
    }
}
