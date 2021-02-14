package com.tools.st;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
public class DateTimeTest {

    @Test
    public void test() {
        log.info("{}", Instant.now().plusSeconds(10).toEpochMilli() - Instant.now().toEpochMilli());
    }

    @Test
    public void testInstant() {
        log.info("{}", Instant.now().toEpochMilli());
//        log.info("{}", LocalDateTime.ofEpochSecond(1612697714L, 940 * 1000 * 1000, ZoneOffset.of()));

        log.info(LocalDateTime.ofInstant(Instant.ofEpochMilli(1612697470298L),
                ZoneId.systemDefault()).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        LocalDateTime.ofInstant(Instant.ofEpochMilli(1612697470298L), ZoneId.of(ZoneId.SHORT_IDS.get("CTT")));
    }
}
