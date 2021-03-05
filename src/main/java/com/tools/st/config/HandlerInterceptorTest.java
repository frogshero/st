package com.tools.st.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class HandlerInterceptorTest implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod hm = ((HandlerMethod)handler);
        log.info("begin handler {} {}.{}", request.getRequestURI(), hm.getBeanType().getName(), hm.getMethod().getName());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerMethod hm = ((HandlerMethod)handler);
        log.info("handler end before render the view {} {}.{}", request.getRequestURI(), hm.getBeanType().getName(), hm.getMethod().getName());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerMethod hm = ((HandlerMethod)handler);
        log.info("after render the view {} {}.{}", request.getRequestURI(), hm.getBeanType().getName(), hm.getMethod().getName());
    }
}
