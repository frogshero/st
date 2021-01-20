package com.tools.st.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyKafkaProcessor {

    @KafkaListener(topics = "test")
    public void processMsg(String content) {
        log.info(content);
    }

}
