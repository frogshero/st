package com.tools.st.controller;

import com.tools.st.advice.InResult;
import com.tools.st.entity.base.StringVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@InResult
@RequestMapping("/tt")
public class ControllerTest {

    @GetMapping("/aa")
    public StringVO ret() {
        StringVO ret = new StringVO();
        ret.setValue("string value");
        return ret;
    }

}
