package com.tools.st;

import com.mysql.cj.MysqlType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class EnumTest {

    @Test
    public void test() {
        List<MysqlType> types = EnumSet.allOf(MysqlType.class).stream().filter(e -> !e.getName().endsWith("UNSIGNED")).collect(Collectors.toList());
        types.stream().forEach(e -> log.info("{}:{}", e.getName(), e.getClassName()));
        log.info("{}", types.size());

    }
}
