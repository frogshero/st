package com.tools.st.controller;

import com.tools.st.advice.InResult;
import com.tools.st.entity.base.StringVO;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
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
}
