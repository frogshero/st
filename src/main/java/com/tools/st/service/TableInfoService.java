package com.tools.st.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tools.st.entity.base.ColumnInfo;
import com.tools.st.mapper.base.TableInfoDao;

@Service
public class TableInfoService implements TableInfoInterface {

  @Autowired
  //@Qualifier
  TableInfoDao tableInfoDao;

  public List<ColumnInfo> getColumnInfos(String schema, String tableName) {
    return tableInfoDao.getColumnInfos(schema, tableName);
  }

  public List<String> getAllTables() {
    return tableInfoDao.getAllTables("jx_mes");
  }

}
