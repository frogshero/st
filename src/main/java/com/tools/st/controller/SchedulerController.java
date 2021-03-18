package com.tools.st.controller;

import com.tools.st.service.ScheduledTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchedulerController {

    @Autowired
    private ScheduledTest scheduledTest;

    @GetMapping("/sc")
    public String testScheduelr() {
        scheduledTest.testExe();
        return "OK";
    }

    @GetMapping("/sc2")
    public String testScheduelr2() {
        scheduledTest.addShort();
        return "OK2";
    }
}
