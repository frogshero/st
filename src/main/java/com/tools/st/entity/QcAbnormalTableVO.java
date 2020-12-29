package com.tools.st.entity;

import com.tools.st.idname.enums.DataTableEnum;
import com.tools.st.idname.service.SetIdName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "检验单")
public class QcAbnormalTableVO extends QcAbnormalTable {
    @SetIdName(type = DataTableEnum.businessDic)
    @ApiModelProperty("检验类别名称")
    private String qcCategoryName;

    @SetIdName(type = DataTableEnum.businessDic)
    @ApiModelProperty("检验结果名称")
    private String ResultName;

    @SetIdName(type = DataTableEnum.businessDic)
    @ApiModelProperty("异常等级名称")
    private String abnormalGradeName;

    @SetIdName(type = DataTableEnum.businessDic)
    @ApiModelProperty("异常分类名称")
    private String abnormalTypeName;

    @SetIdName(type = DataTableEnum.businessDic)
    @ApiModelProperty("处置结果名称")
    private String disposalName;

    @SetIdName(type = DataTableEnum.user)
    @ApiModelProperty("检查员名称")
    private String qcUserName;

    @SetIdName(type = DataTableEnum.user, nameField = "username")
    @ApiModelProperty("检查员代码")
    private String qcUserCode;

    @SetIdName(type = DataTableEnum.businessDic)
    @ApiModelProperty("检验结果")
    private String qcResultName;

    @SetIdName(type = DataTableEnum.user)
    @ApiModelProperty("责任人名称")
    private String responsibleUserName;

    @SetIdName(type = DataTableEnum.user, nameField = "username")
    @ApiModelProperty("责任人代码")
    private String responsibleUserCode;

    @SetIdName(type = DataTableEnum.user)
    @ApiModelProperty("审核人名称")
    private String reviewUserName;

    @SetIdName(type = DataTableEnum.user, nameField = "username")
    @ApiModelProperty("审核人代码")
    private String reviewUserCode;

    @SetIdName(idField = "materielId", nameField = "materielCode", type = DataTableEnum.materiel)
    @ApiModelProperty("零件编号")
    private String partNo;

    @SetIdName(idField = "materielId", nameField = "drawingNumber", type = DataTableEnum.materiel)
    @ApiModelProperty("零件图号")
    private String partDrawingNumber;

    @SetIdName(idField = "materielId", type = DataTableEnum.materiel, nameField = "materielName")
    @ApiModelProperty("零件名称")
    private String partName;

    @ApiModelProperty("零件的BomCode")
    private String bomCode;

    @SetIdName(idField = "productId", nameField = "materielCode", type = DataTableEnum.materiel)
    @ApiModelProperty("产品编号")
    private String productNo;

    @ApiModelProperty("产品图号")
    @SetIdName(idField = "productId", nameField = "drawingNumber", type = DataTableEnum.materiel)
    private String productDrawingNumber;

    @SetIdName(idField = "productId", type = DataTableEnum.materiel, nameField = "materielName")
    @ApiModelProperty("产品名称")
    private String productName;

    @SetIdName(type = DataTableEnum.process, nameField = "processName")
    @ApiModelProperty("检验工序名称")
    private String qcProcessName;

    @SetIdName(type = DataTableEnum.process, nameField = "processName")
    @ApiModelProperty("责任工序名称")
    private String processName;

    @ApiModelProperty("附件数")
    private Integer fileCount;

    @ApiModelProperty("质量评审")
    private String auditCount;

    @SetIdName(type = DataTableEnum.user)
    @ApiModelProperty("维护人名称")
    private String updatedByName;

    @SetIdName(type = DataTableEnum.user, nameField = "username")
    @ApiModelProperty("维护人编号")
    private String updatedByCode;

    @SetIdName(type = DataTableEnum.user)
    @ApiModelProperty("创建人名称")
    private String createdByName;

    @SetIdName(type = DataTableEnum.user, nameField = "username")
    @ApiModelProperty("创建人编号")
    private String createdByCode;

    @SetIdName(idField = "checked", type = DataTableEnum.sysDic, keyPrefix = "audit_status")
    @ApiModelProperty("是否审核")
    private String checkedName;

    @SetIdName(idField = "auditResult", type = DataTableEnum.sysDic, keyPrefix = "approval_status")
    private String auditResultName;

    @SetIdName(idField = "orderId", type = DataTableEnum.order, nameField = "madeNo")
    @ApiModelProperty("订单号")
    private String madeNo;

    @SetIdName(type = DataTableEnum.businessDic, order = 10)
    @ApiModelProperty("制造类型名称")
    private String madeTypeName;

    @SetIdName(idField = "orderId", type = DataTableEnum.order, nameField = "madeTypeId")
    private Long madeTypeId;

//    @ApiModelProperty("责任人部门名称")
//    private String responsibleDeptName;

    /**
     * 只是前端显示审核标记用
     */
    private Date auditTime;

    private String ids;
}
