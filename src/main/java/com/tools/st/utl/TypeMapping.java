package com.tools.st.utl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public enum TypeMapping {
    BIGINT(Long.class, "BIGINT"),
    INT(Integer.class, "INTEGER"),
    INTEGER(Integer.class, "INTEGER"),
    VARCHAR(String.class, "VARCHAR"),
    DATETIME(Date.class, "TIMESTAMP"),
    DECIMAL(BigDecimal.class, "DECIMAL"),
    DATE(Date.class, "TIMESTAMP"),
    TINYINT(Integer.class, "TINYINT"),
    SMALLINT(Integer.class, "SMALLINT"),
    LONGTEXT(String.class, "VARCHAR"),
    CHAR(String.class, "VARCHAR"),
    TIMESTAMP(Timestamp.class, "TIMESTAMP");

    private Class clz;
    private String jdbcType;
    
    TypeMapping(Class clz, String jdbcType) {
        this.clz = clz;
        this.jdbcType = jdbcType;
    }

    public String getImport() {
        return clz.getCanonicalName();
    }

    public String getType() {
        return clz.getName();
    }

    public String getJdbcType() {
        return jdbcType;
    }
}
