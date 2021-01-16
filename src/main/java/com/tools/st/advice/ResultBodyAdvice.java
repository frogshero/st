package com.tools.st.advice;

import com.tools.st.entity.BasicResult;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.MergedAnnotationCollectors;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Set;

@RestControllerAdvice
@Slf4j
//public class ResultBodyAdvice extends AbstractMappingJacksonResponseBodyAdvice {
public class ResultBodyAdvice implements ResponseBodyAdvice {
//AbstractMappingJacksonResponseBodyAdvice

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Object val = body;
        if (returnType.getContainingClass().isAnnotationPresent(InResult.class)) {
            val = new BasicResult(body == null ? "" : body);

        }
        return new MappingJacksonValue(val);
    }
}
