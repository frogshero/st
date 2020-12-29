package com.tools.st.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class EntityBase {
    @ApiModelProperty("有效标志,1有效，0无效")
    private Integer enableflg;

    @ApiModelProperty("创建人")
    private Long createdBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    @ApiModelProperty("创建时间")
    private Date createdTime;

    @ApiModelProperty("维护人")
    private Long updatedBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    @ApiModelProperty("维护时间")
    private Date updatedTime;
}
