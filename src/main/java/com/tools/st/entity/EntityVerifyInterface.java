package com.tools.st.entity;

import java.util.Date;

/**
 * 处理实体类的审核，撤审字段
 */
public interface EntityVerifyInterface {
    void setReviewUserId(Long reviewUserId);
    void setReviewTime(Date reviewTime);
}
