package com.tools.st.exception;

import lombok.Data;

@Data
public class CommonException extends RuntimeException {
    private String code;
    private String message;

    public CommonException(String code, String error) {
        this.code = code;
        this.message = error;
    }

    public String getMessage() {
        return this.message;
    }

    public static CommonException tipMessage(String msg) {
        return new CommonException("ERR1010", msg);
    }
}
