package com.tools.st.produce;

import com.tools.st.tableConfig.dao.SysComTableConfigDao;
import com.tools.st.tableConfig.SysComTableConfigVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TableConfigTest {
    @Autowired
    SysComTableConfigDao tableConfigDao;

    @Test
    public void test() {
        List<SysComTableConfigVO> ret = tableConfigDao.selectByTableId("scheduleWorkerReport");
        int i = 0;
        for (SysComTableConfigVO vo : ret) {
            i ++;
            String type;
            switch (vo.getTitleType()) {
                case "date":
                case "dateTime":
                    type = "Date";
                    break;
                case "number":
                    type = "Double";
                    break;
                default:
                    type = "String";
                    break;
            }
            System.out.println("    @ExcelProperty(value = \""+ vo.getTableTitleName() +"\", index = " + i + ")");
            System.out.println("    @ColumnWidth(15)");
            if (type.equalsIgnoreCase("Date")) {
                System.out.println("    @DateTimeFormat(\"yyyy-MM-dd HH-mm\")");
            }
            System.out.println("    private " + type + " " + vo.getTableTitle() + ";");
            System.out.println("");
        }
    }
}
