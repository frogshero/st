package com.tools.st.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
;

@ApiModel(description = "表单控件分组")
@Data
public class SysComTableGroup extends EntityBase {
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("上级集团/公司 0独立公司 ")
    private Long comId;

    @ApiModelProperty("表格编号")
    private String tableId;

    @ApiModelProperty("组别名称")
    private String groupName;

    @ApiModelProperty("初始化是否显示")
    private String initShowFlg;

    @ApiModelProperty("排序")
    private String sortNo;


}