package com.tools.st.idname.model;

import lombok.Data;

@Data
public class SysUser extends IdNameEntityBase {
    private String username;
    private String name;
    private Long userId;
    private Long comId;
}
