package com.tools.st.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
;

@ApiModel(description = "基础业务类型")
@Data
public class SysBaseBusinessType extends EntityBase {
    @ApiModelProperty("唯一ID")
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("代码")
    private String code;

    @ApiModelProperty("备注")
    private String remark;


}