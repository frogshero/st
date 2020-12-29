package com.tools.st;

import java.util.List;

import com.google.common.collect.Lists;
import com.tools.st.utl.DbToJavaUtl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.tools.st.entity.base.ColumnInfo;
import com.tools.st.mapper.base.TableInfoDao;

import lombok.extern.slf4j.Slf4j;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;


@SpringBootTest
@Slf4j
public class TableInfoTest {

  @Autowired
  TableInfoDao tableInfoDao;


  @Test
  public void testGenerator() {
    List<String> tables = Lists.newArrayList("sys_base_business_data_dict",
            "sys_base_business_type",
            "sys_com_table_select_config",
            "sys_com_table_config",
            "sys_com_page_api_config",
            "sys_com_page_config",
            "sys_menu",
            "db_table_field_config",
            "db_table_config",
            "sys_com_table_group"
    );
    Resource templates = new ClassPathResource("/templates");
    STGroup group = new STGroupDir(templates.getFilename(), '$', '$');

    StringBuffer sb = new StringBuffer();
    for (String tabName : tables) {
      ST st = group.getInstanceOf("generator");
      st.add("tabName", tabName);
      st.add("entityName", DbToJavaUtl.toJavaEntityName(tabName));
      String result = st.render();
      sb.append(result);
    }
    log.info(sb.toString());
  }


  @Test
  public void testTableApi() {
    List<ColumnInfo> columns = tableInfoDao.getColumnInfos(TestConst.SCHEMA, "qc_abnormal_audit");
    ST st = new ST("<cols:{col|audit.<col.columnName> as <col.javaName>, \n}>");
    st.add("cols", columns);
    log.info(st.render());
  }

}
