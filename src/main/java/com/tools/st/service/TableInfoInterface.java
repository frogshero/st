package com.tools.st.service;

import com.tools.st.entity.base.ColumnInfo;

import java.util.List;

public interface TableInfoInterface {

    List<ColumnInfo> getColumnInfos(String schema, String tableName);

    List<String> getAllTables();

}
