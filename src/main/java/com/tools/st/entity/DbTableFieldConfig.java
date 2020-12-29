package com.tools.st.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
;

@ApiModel(description = "数据表字段配置表")
@Data
public class DbTableFieldConfig extends EntityBase {
    @ApiModelProperty("")
    private Long id;

    @ApiModelProperty("表配置id")
    private Long tableConfigId;

    @ApiModelProperty("字段名称")
    private String fieldName;

    @ApiModelProperty("字段中文名称")
    private String fieldCnName;

    @ApiModelProperty("字段数据类型")
    private Integer fieldDataType;

    @ApiModelProperty("字段长度")
    private Integer fieldLength;

    @ApiModelProperty("model属性名称")
    private String modelPropName;

    @ApiModelProperty("mode属性数据类型")
    private String modelPropDataType;

    @ApiModelProperty("关联表达式配置id")
    private Long fieldRelatedConfigId;

    @ApiModelProperty("批量传入字段名称")
    private String batchInputPropName;

    @ApiModelProperty("是否参与数据库查询，在有值的情况下")
    private Integer isDbQuery;

    @ApiModelProperty("是否主键；1：是，0：否")
    private Integer isPrimaryKey;

    @ApiModelProperty("是否是审核字段")
    private Integer isCheckField;

    @ApiModelProperty("是否可以为空；1：是；0：否")
    private Integer isNull;

    @ApiModelProperty("是否唯一性验证；1：是，0：否")
    private Integer isUniqueValid;

    @ApiModelProperty("校验类型：0，不验证；1，只能为数字；2，电话；3，邮箱；4，金额,最多保留两位小数")
    private Integer validType;

    @ApiModelProperty("")
    private String fieldValueExpression;

    @ApiModelProperty("")
    private String remark;


}