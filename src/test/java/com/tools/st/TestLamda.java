package com.tools.st;

import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.function.Consumer;

public class TestLamda {

    private Map<String, Consumer<String>> map = Maps.newHashMap();

    public void consume(String str) {
        System.out.println(str);
    }

    @Test
    public void test() {
        map.put("xxx", this::consume);
        map.get("xxx").accept("fffff");
    }
}
