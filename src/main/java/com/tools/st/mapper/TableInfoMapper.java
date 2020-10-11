package com.tools.st.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.tools.st.entity.ColumnInfo;
import com.tools.st.entity.TestVO;

@Mapper
public interface TableInfoMapper {

  @Select("SELECT table_name as tableName, column_name as columnName FROM information_schema.columns WHERE upper(table_name) = upper(#{tableName})")
  List<ColumnInfo> getColumnInfos(String tableName);
  
  @Select("select * from table1")
  List<TestVO> getTest();
}
