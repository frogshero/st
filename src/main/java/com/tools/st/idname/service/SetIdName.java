package com.tools.st.idname.service;

import com.tools.st.idname.enums.DataTableEnum;

import java.lang.annotation.*;

/**
 * 要过滤和名称转换的字段
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface SetIdName {
    /**
     * 查询哪个表
     * @return
     */
    DataTableEnum type();

    /**
     * 对应的ID字段, 默认去掉Name加Id
     * @return
     */
    String idField() default "";

    /**
     * key的前缀
     * @return
     */
    String keyPrefix() default "";

    /**
     * 从哪个字段查询，默认name
     */
    String nameField() default "name";

    int order() default 0;
}
