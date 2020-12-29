package com.tools.st.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
;

@ApiModel(description = "基础业务数据字典表")
@Data
public class SysBaseBusinessDataDict extends EntityBase {
    @ApiModelProperty("唯一ID")
    private Long id;

    @ApiModelProperty("公司ID")
    private Long comId;

    @ApiModelProperty("父ID")
    private Long parentId;

    @ApiModelProperty("")
    private Integer sortNum;

    @ApiModelProperty("编码")
    private String serialNumber;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("业务类型id")
    private Long businessTypeId;

    @ApiModelProperty("业务类型")
    private String businessType;

    @ApiModelProperty("")
    private String isSystemInternal;

    @ApiModelProperty("")
    private String isDeactivate;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("")
    private String remark1;

    @ApiModelProperty("")
    private String remark2;

    @ApiModelProperty("")
    private String remark3;

    @ApiModelProperty("")
    private String remark4;

    @ApiModelProperty("")
    private String remark5;

    @ApiModelProperty("")
    private String remark6;

    @ApiModelProperty("")
    private String remark7;

    @ApiModelProperty("")
    private String remark8;

    @ApiModelProperty("")
    private String remark9;

    @ApiModelProperty("")
    private String remark10;


}