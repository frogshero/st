package com.tools.st.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
;

@ApiModel(description = "表格过滤字段配置")
@Data
public class SysComTableSelectConfig extends EntityBase {
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("上级集团/公司 0独立公司 ")
    private Long comId;

    @ApiModelProperty("表格编号")
    private String tableId;

    @ApiModelProperty("检索key")
    private String selectKet;

    @ApiModelProperty("标题")
    private String tableTitle;

    @ApiModelProperty("表头名字（为空时，展示表整体设置的信息）")
    private String tableTitleName;

    @ApiModelProperty("表key")
    private String tableKey;

    @ApiModelProperty("表字段code")
    private String keyCode;

    @ApiModelProperty("类型(date、select、components、input)")
    private String titleType;

    @ApiModelProperty("1:标准组件，2:非标组件")
    private String componentsId;

    @ApiModelProperty("")
    private String componentsName;

    @ApiModelProperty("组件类型")
    private String componentsType;

    @ApiModelProperty("组件返回值列表")
    private String componentsResult;

    @ApiModelProperty("接受参数")
    private String titleAccept;

    @ApiModelProperty("快速检索API")
    private String searchApi;

    @ApiModelProperty("")
    private Integer sortNo;


}