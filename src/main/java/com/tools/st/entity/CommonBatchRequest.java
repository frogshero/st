package com.tools.st.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("删除，审核，撤审的参数")
@Data
public class CommonBatchRequest {
    @ApiModelProperty(value = "id数组", required = true)
    private List<Long> ids;
}
