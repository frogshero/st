package com.tools.st.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
;

@ApiModel(description = "页面检索方案")
@Data
public class SysComPageProgramme extends EntityBase {
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("上级集团/公司 0独立公司 ")
    private Long comId;

    @ApiModelProperty("页面编号")
    private Long pageConfigId;

    @ApiModelProperty("方案名称")
    private String programmeName;

    @ApiModelProperty("是否是系统项目")
    private Integer sysFlg;


}