package com.tools.st.exception;

import com.tools.st.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice("com.ymc.mes.mold.warehouse.controller")
public class GlobalExceptionHandler {

    public GlobalExceptionHandler() {
    }

    @ExceptionHandler({CommonException.class})
    @ResponseBody
    public Result handleCommonException(CommonException e) {
        log.error("提示异常：", e);
        return new Result(e.getCode(), e.getMessage());
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public Object globalExceptionHandler(Exception e) {
        log.error("运行异常：", e);
        return new Result("-1", "服务器遇到了意料不到的情况，不能完成客户的请求");
    }

}