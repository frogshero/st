package com.tools.st.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(description = "检验单")
@Data
public class QcAbnormalTable extends EntityBase {
    @ApiModelProperty("唯一ID")
    private Long id;

    @ApiModelProperty("公司标识")
    private Long comId;

    @ApiModelProperty("检验单号")
    private Long qcNo;

    @ApiModelProperty("是否审核")
    private String checked;

    @ApiModelProperty("检验类别,sys_base_business_data_dict.id,testCategory")
    private Long qcCategoryId;

    @ApiModelProperty("检验结果ID")
    private Long qcResultId;

    @ApiModelProperty("检验开始时间")
    private Date qcBeginTime;

    @ApiModelProperty("检验结束时间")
    private Date qcEndTime;

    @ApiModelProperty("检查员,sys_user.user_id")
    private Long qcUserId;

    @ApiModelProperty("零件,base_materiel.id")
    private Long materielId;

    @ApiModelProperty("产品,base_materiel.id")
    private Long productId;

    @ApiModelProperty("订单ID,mold_order.id")
    private Long orderId;

    @ApiModelProperty("检验工序,mold_craft_process.id")
    private Long qcProcessId;

    @ApiModelProperty("检验结果,sys_base_business_data_dict.id,testResult")
    private Long ResultId;

    @ApiModelProperty("异常等级,sys_base_business_data_dict.id,testExceptionGrade")
    private Long abnormalGradeId;

    @ApiModelProperty("异常分类,sys_base_business_data_dict.id,testExceptionType")
    private Long abnormalTypeId;

    @ApiModelProperty("异常描述")
    private String abnormalDesc;

    @ApiModelProperty("处置结果,sys_base_business_data_dict.id,testDisposalResult")
    private Long disposalId;

    @ApiModelProperty("措施及预防")
    private String solution;

    @ApiModelProperty("责任人,sys_user.user_id")
    private Long responsibleUserId;

    @ApiModelProperty("责任工序,mold_craft_process.id")
    private Long processId;

    @ApiModelProperty("终审结果")
    private String auditResult;

    @ApiModelProperty("异常数量")
    private java.math.BigDecimal amtOfAbnormal;

    @ApiModelProperty("损失工时")
    private java.math.BigDecimal amtOfLabor;

    @ApiModelProperty("损失金额")
    private java.math.BigDecimal amtOfLoss;

    @ApiModelProperty("处罚金额")
    private java.math.BigDecimal amtOfPenalty;

    @ApiModelProperty("备注")
    private String remarks;

    @ApiModelProperty("审核人")
    private Long reviewUserId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    @ApiModelProperty("审核时间")
    private Date reviewTime;

}