package com.tools.st.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tools.st.entity.ColumnInfo;
import com.tools.st.service.TableInfoService;

@RestController
@RequestMapping("/tables")
public class TableInfoController {

  @Autowired
  TableInfoService tableInfoService;
  
  @GetMapping("/{tabName}")
  public List<ColumnInfo> getColumns(@PathVariable String tabName) {
    return tableInfoService.getColumnInfos(tabName);
  }
}
