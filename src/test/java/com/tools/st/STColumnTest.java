package com.tools.st;

import com.tools.st.entity.base.ColumnInfo;
import com.tools.st.entity.base.TableInfo;
import com.tools.st.mapper.base.TableInfoDao;
import com.tools.st.service.TableInfoService;
import com.tools.st.utl.FileUtl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.io.IOException;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Slf4j
public class STColumnTest {

    @Autowired
    TableInfoService tableInfoService;

    @Autowired
    TableInfoDao tableInfoDao;

    private void test(List<ColumnInfo> columnInfos, String template, char begin, char end) {
        ST st = new ST(template, begin, end);
        st.add("cols", columnInfos);
        log.info(st.render());
    }


    @Test
    public void testCols() {
        String tabName = "qc_abnormal_table";
        List<ColumnInfo> columns = tableInfoService.getColumnInfos(TestConst.SCHEMA, tabName);
        TableInfo tableInfo = tableInfoDao.getTableInfo(TestConst.SCHEMA, tabName);
//        test(columns, "$cols:{col|abnormal.$col.columnName$ as $col.javaName$}; separator=\",\n\"$", '$', '$');
//        test(columns, "$cols:{col|if(!$col.javaName$.equals(\"id\")) $col.javaName$ <endif>}>; separator=\",\n\"$", '$', '$');
        test(columns, "<cols:{col|<if(!col.autoInc)> <col.javaName> <endif>}; separator=\",\n\">", '<', '>');
//        test(columns, "$cols:{col|<if test=\"$col.javaName$!=null\">and abnormal.$col.columnName$ >= #{qrBeginTime}</if>}; separator=\",\n\"$", '$', '$');

//        test(columnInfos, "$cols:{col|$col.columnName$}; separator=\",\"$", '$', '$');
//        test(columnInfos, "$cols:{col|#{$col.javaName$,jdbcType=$col.jdbcType$\\}}; separator=\",\"$", '$', '$');
//        test(columnInfos, "$cols:{col|$col.columnName$=#{$col.javaName$,jdbcType=$col.jdbcType$\\}}; separator=\",\"$", '$', '$');
    }

    @Test
    public void produceXmlMapper() throws IOException {
        String tabName = "sys_menu";
        STGroup xmlGroup = new STGroupFile(new ClassPathResource("/templates/xml.stg").getFile().getCanonicalPath(), '$', '$');
        List<ColumnInfo> columns = tableInfoService.getColumnInfos(TestConst.SCHEMA, tabName);
        Assertions.assertTrue(columns.size() > 0);

        ColumnInfo keyCol = columns.stream().filter(e -> e.getKey()).findFirst().get();
        Assertions.assertNotNull(keyCol);

        TableInfo tableInfo = tableInfoDao.getTableInfo(TestConst.SCHEMA, tabName);
        Assertions.assertNotNull(tableInfo);

        ST xmlST = xmlGroup.getInstanceOf("myxml");
        xmlST.add("entityDir", "com.tools.st.entity");
        xmlST.add("tab", tableInfo);
        xmlST.add("cols", columns);
        xmlST.add("keyCol", keyCol);
//        log.info(xmlST.render(80));

        FileUtl.writeStrToFile(xmlST.render(80), TestConst.BASE_SOURCE + "\\mapper\\SysMenuMapper.xml");
    }
}
