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

import java.util.Set;

@RestControllerAdvice
@Slf4j
public class ResultBodyAdvice extends AbstractMappingJacksonResponseBodyAdvice {

    @Override
    protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType, MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {
        //Controller方法的返回值必须是对象
//        Set<InResult> ret = MergedAnnotations.from(returnType.getContainingClass()).stream(InResult.class)
//                .collect(MergedAnnotationCollectors.toAnnotationSet());
        if (returnType.getContainingClass().isAnnotationPresent(InResult.class)) {
            if (!(bodyContainer.getValue() instanceof BasicResult)) {
                BasicResult<?> result = new BasicResult(bodyContainer.getValue());
                bodyContainer.setValue(result);
            }
        }
    }
}
