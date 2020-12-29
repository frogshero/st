package com.tools.st.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
;

@ApiModel(description = "页面配置信息")
@Data
public class SysComPageConfig extends EntityBase {
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("上级集团/公司 0独立公司 ")
    private Long comId;

    @ApiModelProperty("页面编号")
    private String pageId;

    @ApiModelProperty("标题名称")
    private String pageTitleName;

    @ApiModelProperty("页面方案关联编号")
    private String pageProgrammeId;

    @ApiModelProperty("页面类别(0:标准页面 1:带菜单不展开树形 2:不展开左右表 3:带菜单展开树形 4:展开左右表 5:不带菜单展开树形 6:不带菜单不展开树形)")
    private String pageType;

    @ApiModelProperty("页面所需组件名称（‘，’分开）")
    private String pageComponents;

    @ApiModelProperty("2个值，0位置：要参与检索的treeKey对应的字段；1位置要参与检索的label对应的tableTitle")
    private String treeCode;

    @ApiModelProperty("新增编辑页面类型（0:标准结构 1:带表头）")
    private String addType;

    @ApiModelProperty("")
    private String acceptCode;


}