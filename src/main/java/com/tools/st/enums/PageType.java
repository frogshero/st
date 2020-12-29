package com.tools.st.enums;

import lombok.Getter;

@Getter
public enum PageType {
    stand("0"), menu("1"), leftRight("2");

    private String value;

    PageType(String val) {
        this.value = val;
    }
}
