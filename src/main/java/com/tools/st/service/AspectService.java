package com.tools.st.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AspectService implements AspectServiceIntf {

    @Override
    public String testAspect() {
        return "TEST";
    }
}
