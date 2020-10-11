package com.tools.st;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tools.st.entity.ColumnInfo;
import com.tools.st.mapper.TableInfoMapper;
import com.tools.st.utl.JsonUtl;

import lombok.extern.slf4j.Slf4j;



@SpringBootTest
@Slf4j
public class TableInfoTest {

  @Autowired
  TableInfoMapper tableInfoMapper;
  
  @Test
  public void test() throws IOException {
    List<ColumnInfo> columns = tableInfoMapper.getColumnInfos("test3");
    assertNotNull(columns);
    assertTrue(columns.size() > 0);
    
    Resource testSource = new ClassPathResource("/columns.json");
    JsonUtl.toFile(testSource.getFile(), columns);
    
    
  }
  

}
