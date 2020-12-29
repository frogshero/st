package com.tools.st.enums;

import lombok.Getter;

@Getter
public enum PageAddEditType {
    stand("0"), title("1");

    private String value;

    PageAddEditType(String val) {
        this.value = val;
    }

}
