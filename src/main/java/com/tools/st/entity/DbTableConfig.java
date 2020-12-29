package com.tools.st.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
;

@ApiModel(description = "数据库表信息配置")
@Data
public class DbTableConfig extends EntityBase {
    @ApiModelProperty("")
    private Long id;

    @ApiModelProperty("")
    private Long comId;

    @ApiModelProperty("数据库表名")
    private String tableName;

    @ApiModelProperty("前端配置项中的table_id")
    private String tableId;

    @ApiModelProperty("查询语句中的别名，最好要保证唯一性，至少在会存在关联的表中是唯一的")
    private String alias;

    @ApiModelProperty("")
    private String remark;


}