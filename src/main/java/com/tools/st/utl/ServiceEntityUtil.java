package com.tools.st.utl;

import com.tools.st.entity.EntityAuditInterface;
import com.tools.st.entity.EntityVerifyInterface;
import com.tools.st.enums.OperationEnum;
import com.tools.st.idname.model.SysUser;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ServiceEntityUtil {
//    @Autowired
//    SysUserService sysUserService;

    private SysUser getUser() {
        return new SysUser();
    }

    /**
     * 处理审计字段
     * @param auditEntity
     * @param operationEnum
     */
    public void setAuditFields(EntityAuditInterface auditEntity, OperationEnum operationEnum) {
        //enableflg,createdBy,createdTime,updatedBy,updatedTime
        SysUser user = getUser();
        if (operationEnum == OperationEnum.insert) {
            auditEntity.setEnableflg(1);
            auditEntity.setCreatedBy(user.getUserId());
            auditEntity.setCreatedTime(new Date());
        }
        if (operationEnum == OperationEnum.delete) {
            auditEntity.setEnableflg(0);
        }
        if (auditEntity.getComId() == null) {
            auditEntity.setComId(user.getComId());
        }
        auditEntity.setUpdatedBy(user.getUserId());
        auditEntity.setUpdatedTime(new Date());
    }

    /**
     * 处理审核，撤审
     */
    public void setVerifiyFields(EntityVerifyInterface voForCheck, boolean checked) {
        if (checked) {
            //审核人
            voForCheck.setReviewUserId(getUser().getUserId());
            voForCheck.setReviewTime(new Date());
        } else {
            voForCheck.setReviewUserId(null);
            voForCheck.setReviewTime(null);
        }
    }
}
