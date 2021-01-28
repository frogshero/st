package com.tools.st.config;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

@Aspect
@Slf4j
@Component
public class ServiceAspect {

    private Pattern pattern = Pattern.compile("add|create|insert|edit|update|delete|print", CASE_INSENSITIVE);

    /**@Pointcut参数怎么绑定的？
     * getBindingAtJoinPoint:135, ReflectionVar (org.aspectj.weaver.reflect)
     * getPointcutParameters:108, ShadowMatchImpl (org.aspectj.weaver.reflect)
     * matchesJoinPoint:88, ShadowMatchImpl (org.aspectj.weaver.reflect)
     * matches:357, AspectJExpressionPointcut (org.springframework.aop.aspectj)
     * proceed:174, ReflectiveMethodInvocation (org.springframework.aop.framework)
     * proceed:749, CglibAopProxy$CglibMethodInvocation (org.springframework.aop.framework)
     * invoke:95, ExposeInvocationInterceptor (org.springframework.aop.interceptor)
     */
    /**
     * AfterReturningAdviceInterceptor-> AspectJAfterReturningAdvice -> @Aspect
     * @param jp
     * @param svr
     */
    @AfterReturning(pointcut = "execution(public * com.tools.st.service.*.*(..)) && target(svr)")
    public void intercept(JoinPoint jp, Object svr) {
        Matcher m = pattern.matcher(jp.getSignature().getName());
        if (m.find()) {
            log.info("{}.{}", svr.toString(), jp.getSignature().getName());
        }
    }

    //tt必须 && @target(tt)
    @Around("execution(public * com.tools.st.service.*.*(..)) && @target(tt)")
    public Object around(ProceedingJoinPoint pjp, Api tt) throws Throwable {
        log.info("");
        return pjp.proceed();
    }

}
