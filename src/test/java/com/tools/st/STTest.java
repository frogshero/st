package com.tools.st;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;
import org.stringtemplate.v4.STGroupFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tools.st.entity.ColumnInfo;
import com.tools.st.vo.User;
import org.stringtemplate.v4.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class STTest {
  
  @Test
  public void testST() {
    ST hello = new ST("Hello, <name>");
    hello.add("name", "World");
    System.out.println(hello.render());
  }
  
  @Test
  public void testGroup() {
    Resource templates = new ClassPathResource("/templates");
    STGroup group = new STGroupDir(templates.getFilename());
    ST st = group.getInstanceOf("decl");
    st.add("type", "int");
    st.add("name", "x");
    st.add("value", 0);
    String result = st.render(); 
    log.info(result);
  }
  
  @Test
  public void testGrpFile() throws IOException {
    STGroup group = new STGroupFile(new ClassPathResource("/templates/dynamicScoping.stg").getFile().getCanonicalPath());
    ST st = group.getInstanceOf("decl");
    st.add("type", "int");
    st.add("name", "x");
    st.add("value", 0);
    String result = st.render();
    log.info(result);
  }
  
  @Test
  public void testProp() {
    ST st = new ST("<b>$u.id$</b>: $u.name$", '$', '$');
    st.add("u", new User(999, "parrt"));
    String result = st.render();
    log.info(result);
  }
  
  @Test
  public void testIt() {
    ST st = new ST("<aa:{itt|<itt.id>: <itt.lastName>, <itt.firstName>\n}>");
    st.addAggr("aa.{firstName ,lastName, id }", "Ter", "Parr", 99); // add() uses varargs
    st.addAggr("aa.{firstName, lastName ,id}", "Tom", "Burns", 34);
    log.info(st.render());
  }
  
  @Test
  public void testCols() throws JsonParseException, JsonMappingException, IOException {
    File src = Paths.get("E:\\wgf\\java\\source\\st\\st\\src\\test\\resources\\data\\columns.json").toFile();
    ObjectMapper om = new ObjectMapper();
    List<ColumnInfo> cols = om.readValue(src, new TypeReference<List<ColumnInfo>>() {});
    ST st2 = new ST("<items:{it|<it.id>: <it.lastName>, <it.firstName>\n}>");
    st2.addAggr("items.{ firstName ,lastName, id }", "Ter", "Parr", 99); // add() uses varargs
    st2.addAggr("items.{firstName, lastName ,id}", "Tom", "Burns", 34);
    log.info(st2.render());
    
    ST st = new ST("<cols:{col|@ApiModelProperty(\"<col.columnComment>\")\nprivate col.javaType <col.columnName>;\n\n}>");
    for (ColumnInfo col : cols) {
      st.addAggr("cols.{columnName}", col.getColumnName());
    }
    log.info(st.render());
  }
  
  public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {

    File src = Paths.get("E:\\wgf\\java\\source\\st\\st\\src\\test\\resources\\data\\columns.json").toFile();
    ObjectMapper om = new ObjectMapper();
    List<ColumnInfo> cols = om.readValue(src, new TypeReference<List<ColumnInfo>>() {});
    ST st2 = new ST("<items:{it|<it.id>: <it.lastName>, <it.firstName>\n}>");
    st2.addAggr("items.{ firstName ,lastName, id }", "Ter", "Parr", 99); // add() uses varargs
    st2.addAggr("items.{firstName, lastName ,id}", "Tom", "Burns", 34);
    log.info(st2.render());
    
    ST st = new ST("<cols:{col|@ApiModelProperty(\"<col.columnComment>\")\nprivate <col.javaType> <col.columnName>;\n\n}>");
    for (ColumnInfo col : cols) {
      st.addAggr("cols.{columnName}", col.getColumnName());
    }
    log.info(st.render());
  }
}

