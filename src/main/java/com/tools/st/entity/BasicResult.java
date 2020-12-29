package com.tools.st.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BasicResult<T> {
    private String code = "0";

    private String message = "";

    private T data;

    private long total;

    private long totalForSort;

}
