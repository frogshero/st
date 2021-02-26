package com.tools.st.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Result<T> {
    private String code = "0";

    private String message = "";

    private T data;

    private long total;

    private long totalForSort;

    public Result(T data) {
        this.data = data;
    }

    public Result(String code, String msg) {
        this.code = code;
        this.message = msg;
        this.data = null;
    }
}
