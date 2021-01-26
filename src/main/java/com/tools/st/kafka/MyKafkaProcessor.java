package com.tools.st.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyKafkaProcessor {

    // 发送消息时要指定分区或key
    // 每个IP固定partition,保证单partition有序处理,保证并发不同partiiton之间不会干扰对方
    @KafkaListener(topics = "test")
    public void processMsg(String content) {
        log.info(content);
    }

}
