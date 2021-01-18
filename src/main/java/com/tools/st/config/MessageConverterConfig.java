package com.tools.st.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.time.format.DateTimeFormatter;
import java.util.Collections;

@Configuration
public class MessageConverterConfig {

    //JacksonAutoConfiguration.jacksonObjectMapperBuilder
    @Bean
    Jackson2ObjectMapperBuilderCustomizer LocalDateTimeCustomizer() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return jacksonObjectMapperBuilder -> {
            jacksonObjectMapperBuilder.serializers(new LocalDateTimeSerializer(dtf));
            jacksonObjectMapperBuilder.deserializers(new LocalDateTimeDeserializer(dtf));
        };
    }

    //原来在HttpMessageConvertersAutoConfiguration, 整个消息转换服务只保留一个：MappingJackson2HttpMessageConverter
    //消息服务在RequestResponseBodyMethodProcessor中转换responseBody时使用
    @Bean
    public HttpMessageConverters messageConverters(ObjectProvider<HttpMessageConverter<?>> converters,
                                                   Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
        //jacksonObjectMapperBuilder定义在JacksonAutoConfiguration
        //Api Server 只要一个MappingJackson2HttpMessageConverter就够了
        //得用注入，不能new，不然上面的Jackson2ObjectMapperBuilderCustomizer不会被调用
//        Jackson2ObjectMapperBuilder builder = Jackson2ObjectMapperBuilder.json();
//        builder.applicationContext(applicationContext);
        MappingJackson2HttpMessageConverter jacksonConverter = (new MappingJackson2HttpMessageConverter(jacksonObjectMapperBuilder.build()));
        return new HttpMessageConverters(false, Collections.singleton(jacksonConverter));
    }
}
