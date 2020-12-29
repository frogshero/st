package com.tools.st.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
;

@ApiModel(description = "菜单管理")
@Data
public class SysMenu extends EntityBase {
    @ApiModelProperty("公司id")
    private Long comId;

    @ApiModelProperty("")
    private Long menuId;

    @ApiModelProperty("父菜单ID，一级菜单为0")
    private Long parentId;

    @ApiModelProperty("菜单分级（0：运行商使用的菜单，1：基础菜单，2：集团菜单，3：压铸菜单，4：机加菜单，5：模具菜单，9：通用菜单）")
    private String level;

    @ApiModelProperty("菜单名称")
    private String name;

    @ApiModelProperty("菜单URL")
    private String url;

    @ApiModelProperty("授权(多个用逗号分隔，如：user:list,user:create)")
    private String perms;

    @ApiModelProperty("类型   0：目录   1：菜单   2：按钮")
    private Integer type;

    @ApiModelProperty("菜单图标")
    private String icon;

    @ApiModelProperty("排序")
    private Integer orderNum;

    @ApiModelProperty("菜单类别(PC端:0，手机端:1)")
    private String menuType;

    @ApiModelProperty("菜单展示开关(展示:1，不展示:0)")
    private Integer showFlg;


}