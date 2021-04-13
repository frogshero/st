package com.tools.st.entity;

import java.util.Date;

/**
 * 处理实体类的审计字段
 */
public interface EntityAuditInterface {
    default Long getComId() {return null;}
    default void setComId(Long comId) {}
    default void setEnableflg(Integer enableflg) {}
    default void setCreatedBy(Long createdBy) {}
    default void setCreatedTime(Date createdTime) {}
    default void setUpdatedBy(Long updatedBy) {}
    default void setUpdatedTime(Date updatedTime) {}
}
