package com.tools.st.vo;

import com.tools.st.utl.StrUtl;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;

import java.util.List;

@Data
public class MybatisCreateParam {
    //调用指定的属性
    private String schema;
    private String tabName;
    private String homePackage;
    private String daoPostfix;
    private String voPostfix;
    private String daoBase;
    private String daoBasePackage;
    private String voBase;
    private String voBasePackage;
    private String chineseDesc;
    //编号字段
    private String noField;

    //下面是计算出的属性
    private String modelPackage;
    private String daoPackage;

    private String javaName;
    private String shortName;

    private String daoBaseClzFullName;
    private String voBaseClzFullName;

    private String voClzName;
    private String daoClzName;
    private String voClzFullName;
    private String daoClzFullName;
    private String serviceVariable;
    private String voVariable;
    private String daoVariable;
    private String serviceClzName;
    private String controllerClzName;

    private List<String> auditFields = Lists.newArrayList();
    private String servicePackage;
    private String controllerPackage;
    private String entityChinese;
    private String requestMapping;
    private String serviceClzFullName;
    private String lJavaName;
//    private String entityRequestClz;
//    private String entityRequestVariable;
//    private String listRequestClz;
//    private String listRequestVariable;
    private String entityClzFullName;

    public boolean isAuditField(String name) {
        return auditFields.indexOf(name) >= 0;
    }

    public void init() {
        modelPackage = homePackage + ".model";
        daoPackage = homePackage + ".dao";
        servicePackage = homePackage + ".service";
        controllerPackage = homePackage + ".controller";

        this.shortName = StrUtl.getShortName(tabName);
        this.lJavaName = StrUtl.getObjName(tabName);
        this.javaName = StringUtils.capitalize(this.lJavaName);
        this.entityClzFullName = modelPackage + "." + javaName;
        
        this.voClzName = javaName + voPostfix;
        this.daoClzName = javaName + daoPostfix;
        this.voClzFullName = modelPackage + "." + javaName + voPostfix;
        this.daoClzFullName = daoPackage + "." + javaName + daoPostfix;
        if (StringUtils.isNotBlank(daoBase)) {
            this.daoBaseClzFullName = daoBasePackage + "." + daoBase;
        }
        if (StringUtils.isNotBlank(voBase)) {
            this.voBaseClzFullName = voBasePackage + "." + voBase;
        }

        this.serviceClzName = this.javaName + "Service";
        this.serviceClzFullName = this.servicePackage + "." + this.javaName + "Service";        
        this.controllerClzName = this.javaName + "Controller";
        this.serviceVariable = StringUtils.uncapitalize(this.javaName) + "Service";
        this.voVariable = StringUtils.uncapitalize(this.voClzName);
        this.daoVariable = StringUtils.uncapitalize(this.daoClzName);

//        this.listRequestClz = this.javaName + "Request";
//        this.entityRequestClz = this.javaName + "EditRequest";
    }

}
