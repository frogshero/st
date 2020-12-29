package com.tools.st.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;;

@ApiModel(description = "检验单-审核")
@Data
public class QcAbnormalAudit extends EntityBase {
    @ApiModelProperty("唯一ID")
    private Long id;

    @ApiModelProperty("检验单,qc_abnormal_table.id")
    private Long qcId;

    @ApiModelProperty("评审部门,sys_dept.dept_id")
    private Long auditDeptId;

    @ApiModelProperty("评审人,sys_user.user_id")
    private Long auditUserId;

    @ApiModelProperty("意见描述")
    private String auditReason;

    @ApiModelProperty("提交时间")
    private Date submitTime;

    @ApiModelProperty("取消,1/0")
    private Integer cancelled;

    @ApiModelProperty("是否同意,sys_dict,yes_or_no")
    private String auditResultCode;


}