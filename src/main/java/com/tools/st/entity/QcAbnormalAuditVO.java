package com.tools.st.entity;

import com.tools.st.idname.enums.DataTableEnum;
import com.tools.st.idname.service.SetIdName;
import lombok.Data;

@Data
public class QcAbnormalAuditVO extends QcAbnormalAudit {
    @SetIdName(type = DataTableEnum.dept)
    private String auditDeptName;
    @SetIdName(type = DataTableEnum.user)
    private String auditUserName;
    @SetIdName(type = DataTableEnum.user)
    private String updatedByName;
    @SetIdName(type = DataTableEnum.sysDic, keyPrefix = "yes_or_no", idField = "auditResultCode")
    private String auditResultName;

    private String ids;
}
