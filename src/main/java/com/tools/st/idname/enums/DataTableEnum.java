package com.tools.st.idname.enums;

import com.tools.st.idname.dao.*;
import lombok.Getter;

@Getter
public enum DataTableEnum {
    /**
     * 用户sys_user
     */
    user(UserDao.class, "sys_user", "U", "user_id", false),
    /**
     * 部门
     */
    dept(DeptDao.class, "sys_dept", "D", "dept_id", false),
    /**
     * 物料base_materiel
     */
    materiel(MaterielDao.class, "base_materiel","M", "id", false),
    /**
     * 系统字典表sys_dict,不保存id，保存code，不能处理
     * 过滤不用关联这个表
     */
    sysDic(SysDictDao.class, "sys_dict", "SD", "value", true),
    /**
     * id字段必须设置为String
     * 业务字典表sys_base_business_data_dict
     * 过滤不用关联这个表
     */
    businessDic(BusinessDictDao.class, "sys_base_business_data_dict", "BD", "id", false),
    /**
     * 工序mold_craft_process
     */
    process(ProcessDao.class,"mold_craft_process","P", "id", false),
    /**
     * 订单
     */
    order(MoldOrderDao.class, "mold_order", "O", "id", false);
    private String tableName;
    private String shortName;
    private String keyField;
    private Class mapperClass;
    private boolean queryByType;

    DataTableEnum(Class mapperClass, String tableName, String shortName, String keyField, boolean queryByType) {
        this.mapperClass = mapperClass;
        this.tableName = tableName;
        this.shortName = shortName;
        this.keyField = keyField;
        this.queryByType = queryByType;
    }
}
