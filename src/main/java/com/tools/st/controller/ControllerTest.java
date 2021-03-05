package com.tools.st.controller;

import com.tools.st.config.advice.InResult;
import com.tools.st.entity.base.StringVO;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@InResult
@RequestMapping("/tt")
@Timed
@Slf4j
public class ControllerTest {

//    @Autowired
//    AspectTestIntf intf;

    @GetMapping("/ss")
    public String ss() {
        return "string value";
    }

    @GetMapping("/ssvo")
    public StringVO ssvo() {
        StringVO ret = new StringVO();
        ret.setValue("string value");
        return ret;
    }

    @GetMapping("/ii")
    public Integer ii() {
        return 123;
    }

    @GetMapping("/vv")
    public void vv() {
        log.info("void called");
    }

    @GetMapping("/ldt")
    public LocalDateTime ldt() {
        return LocalDateTime.now();
    }

    //-XX:+PrintGC
    @GetMapping("/gc")
    public void aa() throws InterruptedException {
//        new Thread(
//            () -> {
                if (true) {
                    byte[] placeHolder = new byte[64 * 1024 * 1024];
                    System.out.println(placeHolder.length / 1024);
                }
                System.gc();
//            }
//        ).start();
    }
}
