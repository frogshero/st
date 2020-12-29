package com.tools.st.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
;

@ApiModel(description = "页面api配置信息")
@Data
public class SysComPageApiConfig extends EntityBase {
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("上级集团/公司 0独立公司 ")
    private Long comId;

    @ApiModelProperty("页面编号")
    private Long pageConfigId;

    @ApiModelProperty("api类型（1:接口、2:组件、3:跳转页面)")
    private String pageApiType;

    @ApiModelProperty("一级菜单")
    private String firstName;

    @ApiModelProperty("二级菜单")
    private String secondName;

    @ApiModelProperty("三级菜单")
    private String thirdName;

    @ApiModelProperty("api名称")
    private String pageApiName;

    @ApiModelProperty("api地址")
    private String pageApi;

    @ApiModelProperty("请求类型")
    private String sendType;

    @ApiModelProperty("按钮调用组件名称以及组件接收变量名称")
    private String componentsId;

    @ApiModelProperty("返回参数")
    private String componentsResult;

    @ApiModelProperty("接受参数")
    private String titleAccept;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("是否是工具类(0:否 1:是 2:api)")
    private Integer toolsFlg;

    @ApiModelProperty("作为组件时是否显示")
    private Integer componentsToolsFlg;

    @ApiModelProperty("是否作为新增工具栏显示")
    private Integer addToolsFlg;

    @ApiModelProperty("是否作为编辑工具栏显示")
    private Integer editToolsFlg;

    @ApiModelProperty("作为上下结构新增副表时，显示工具条")
    private Integer addTableToolsFlg;

    @ApiModelProperty("排序")
    private Integer sortNo;


}