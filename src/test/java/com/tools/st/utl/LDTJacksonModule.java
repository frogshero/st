package com.tools.st.utl;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LDTJacksonModule extends SimpleModule {
    public LDTJacksonModule() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dtf));
        addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dtf));
    }
}
