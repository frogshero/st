package com.tools.st.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tools.st.entity.ColumnInfo;
import com.tools.st.entity.TestVO;
import com.tools.st.mapper.TableInfoMapper;

@Service
public class TableInfoService {
  
  @Autowired
  TableInfoMapper tableInfoMapper;
  
  
  public List<ColumnInfo> getColumnInfos(String tableName) {
    List<TestVO> test = tableInfoMapper.getTest();
    return tableInfoMapper.getColumnInfos(tableName);
  }

}
