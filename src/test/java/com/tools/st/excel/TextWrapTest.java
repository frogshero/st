package com.tools.st.excel;

import org.apache.poi.xssf.usermodel.*;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class TextWrapTest {

    @Test
    public void getExcel1() throws IOException {

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("aaaa");
        arrayList.add("bbbb");
        arrayList.add("cccc");
        arrayList.add("dddd");
        String content = String.join("\n", arrayList);

        XSSFWorkbook workBook = new XSSFWorkbook();
        XSSFSheet sheet = workBook.createSheet("测试导出");

        XSSFRow row1 = sheet.createRow(1);
        XSSFCell cell = row1.createCell(0);
        // 换行==>>自定义单元格内容换行规则
//        XSSFCellStyle cs = workBook.createCellStyle();
//        cs.setWrapText(true);
//        cell.setCellStyle(cs);

        cell.getCellStyle().setWrapText(true);  //直接这样不行，得new一个style
        // 设置要导出的文件的名字
        String fileName = "myExport.xlsx";
        String[] headers = { "单元格00","单元格01","单元格02" };

        XSSFRow titleRow = sheet.createRow(0);
        // 在excel表中添加表头
        for (int i = 0; i < headers.length; i++) {
            titleRow.createCell(i).setCellValue(headers[i]);
        }

        cell.setCellValue(content);


        try (FileOutputStream fos = new FileOutputStream("E:\\tmp\\ttt.xlsx")) {
            workBook.write(fos);
        }
//        response.setContentType("application/octet-stream");
//        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
//        response.flushBuffer();
    }
}
