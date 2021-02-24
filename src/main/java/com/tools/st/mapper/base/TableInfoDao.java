package com.tools.st.mapper.base;

import java.util.List;

import com.tools.st.entity.base.TableInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.tools.st.entity.base.ColumnInfo;

@Mapper
public interface TableInfoDao {

  @Select("SELECT table_name as tableName, column_name as columnName, data_type as dataType, column_comment columnComment, " +
          "NUMERIC_PRECISION as numericPrecision, NUMERIC_SCALE as numericScale, CHARACTER_MAXIMUM_LENGTH as colLen, " +
          " COLUMN_KEY as columnKey, EXTRA as extra, IS_NULLABLE as nullAble " +
          " FROM information_schema.columns " +
          " WHERE upper(table_schema)=upper(#{schema}) and upper(table_name) = upper(#{tableName})")
  List<ColumnInfo> getColumnInfos(String schema, String tableName);

  @Select("select table_name from information_schema.tables where upper(table_schema)=upper(#{schema})")
  List<String> getAllTables(String schema);

  @Select("select table_name as tableName, table_comment as tableComment from information_schema.tables" +
          " where upper(table_schema)=upper(#{schema}) and upper(table_name)=upper(#{tableName})")
  TableInfo getTableInfo(String schema, String tableName);
}
