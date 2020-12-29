package com.tools.st.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
;

@ApiModel(description = "角色与菜单对应关系")
@Data
public class SysRoleMenu extends EntityBase {
    @ApiModelProperty("")
    private Long id;

    @ApiModelProperty("角色ID")
    private Long roleId;

    @ApiModelProperty("菜单ID")
    private Long menuId;


}