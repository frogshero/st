package com.tools.st;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"test.jvm.prop=gggggggood"}, args = {"--test.jvm.prop=wtf"}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Slf4j
public class EnvironmentVarTest {

    @Autowired
    StApplication stApplication;

    @Test
    public void test() {
        log.info(stApplication.getTestProp());
        Assertions.assertTrue(stApplication.getTestProp().equals("gggggggood"));
    }
}
