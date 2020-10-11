package com.tools.st.entity;

import lombok.Data;

@Data
public class ColumnInfo {
    private String tableName;
    private String columnName;
    /**
     * varchar bigint longtext datetime int tinyint
      decimal double char timestamp set enum longblob
      mediumtext smallint text blob float time
     */
    private String dataType;
    private String columnComment;
    
    public String getJavaName() {
      
      return "";
    }
    
    public String getJavaType() {
      switch (dataType) {
      case "varchar":
      case "longtext":
      case "char":
      case "mediumtext":
      case "text":
      case "blob":
        return "String";
      case "bigint":
        return "Long";
      case "datetime":
      case "timestamp":
      case "time":
        return "Date";
      case "int":
      case "smallint":
      case "tinyint":
        return "Integer";
      case "decimal":
      case "double":
      case "float":
        return "BigDecimal";
      default:
          throw new RuntimeException("not supported" + dataType);
//      case "set":
//      case "enum":
//      case "longblob":
      }
    }
}
