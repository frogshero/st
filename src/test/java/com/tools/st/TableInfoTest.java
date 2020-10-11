package com.tools.st;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tools.st.entity.ColumnInfo;
import com.tools.st.mapper.TableInfoMapper;
import org.stringtemplate.v4.*;

@SpringBootTest
public class TableInfoTest {

  @Autowired
  TableInfoMapper tableInfoMapper;
  
  @Test
  public void test() {
    List<ColumnInfo> columns = tableInfoMapper.getColumnInfos("test3");
    assertNotNull(columns);
    assertTrue(columns.size() > 0);
  }
  

}
