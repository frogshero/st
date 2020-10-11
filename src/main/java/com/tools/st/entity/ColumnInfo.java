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
    private String dateType;
    private String columnComment;
    
}
