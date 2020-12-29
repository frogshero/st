package com.tools.st.idname.model;

import lombok.Data;

@Data
public class IdNameEntityBase {
    private Long id;
    /**
     * 只有sys_dict用
     */
    private String type;
}
