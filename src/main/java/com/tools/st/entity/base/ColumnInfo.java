package com.tools.st.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tools.st.utl.StrUtl;
import com.tools.st.utl.TypeMapping;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Setter
public class ColumnInfo {
    public static String PRIMARY_KEY = "PRI";
    public static String AUTO_INC = "auto_increment";

    private String tableName;
    private String columnName;
    /**
     * varchar bigint longtext datetime int tinyint
      decimal double char timestamp set enum longblob
      mediumtext smallint text blob float time
     */
    private String dataType;
    private String columnComment;

    //字符长度
    private Long colLen;
    //整数位
    private Long numericPrecision;
    //小数位
    private Long numericScale;

    //PRI
    private String columnKey;

    //auto_increment
    private String extra;

    @JsonIgnore
    public String getJavaName() {
      return toJavaColName(columnName);
    }

    @JsonIgnore
    public String getJavaType() {
        return toJavaType(dataType);
    }

    @JsonIgnore
    public String getJdbcType() {
        return toJdbcType(dataType);
    }

    public String getImport() {
        return toImport(dataType);
    }

    public String getName() {
        return columnName;
    }

    public String getType() {
        return dataType;
    }

    public String getComment() {
        return columnComment.replaceAll("\\n", "");
    }

    @JsonIgnore
    public boolean isAutoInc() {
        return extra.equals(AUTO_INC);
    }

    @JsonIgnore
    public boolean getKey() {
        return columnKey.equals(PRIMARY_KEY);
    }

    public String toJavaColName(String columnName) {
        return StrUtl.getObjName(columnName);
    }

    public String toJdbcType(String dataType) {
        String jdbc = TypeMapping.valueOf(dataType.toUpperCase()).getJdbcType();
//        if (jdbc == "INTEGER") {
//            if (numericPrecision <= 3) {
//                return "TINYINT";
//            } else if (numericPrecision <= 5) {
//                return "SMALLINT";
//            } else if (numericPrecision <= 8) {
//                return "INTEGER";
//            } else {
//                return "BIGINT";
//            }
//        } else {
            return jdbc;
//        }
    }

    public String toJavaType(String dataType) {
        return TypeMapping.valueOf(dataType.toUpperCase()).getType();
    }

    public String toImport(String dataType) {
        return TypeMapping.valueOf(dataType.toUpperCase()).getImport();
    }

    public String getSetter() {
        return "set" + StringUtils.capitalize(getJavaName());
    }
}
