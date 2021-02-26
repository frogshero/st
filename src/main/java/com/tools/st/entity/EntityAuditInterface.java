package com.tools.st.entity;

import java.util.Date;

/**
 * 处理实体类的审计字段
 */
public interface EntityAuditInterface {
    Long getComId();
    void setComId(Long comId);

    void setEnableflg(Integer enableflg);
    void setCreatedBy(Long createdBy);
    void setCreatedTime(Date createdTime);
    void setUpdatedBy(Long updatedBy);
    void setUpdatedTime(Date updatedTime);
}
