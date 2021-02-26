package com.tools.st.config.advice;

import com.tools.st.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

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
            val = new Result(body == null ? "" : body);

        }
        return new MappingJacksonValue(val);
    }
}
