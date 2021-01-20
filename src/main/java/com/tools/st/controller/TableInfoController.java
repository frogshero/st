package com.tools.st.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tools.st.entity.base.ColumnInfo;
import com.tools.st.service.TableInfoService;

@CrossOrigin(value = {"*"}, allowedHeaders = {"*"})
@RestController
@RequestMapping("/tables")
@Api("table api")
public class TableInfoController {

  @Autowired
  TableInfoService tableInfoService;

  @ApiOperation("字段信息")
  @GetMapping("database/{schema}/{tabName}")
  public List<ColumnInfo> getColumns(@PathVariable String schema, @PathVariable String tabName) {
    return tableInfoService.getColumnInfos(schema, tabName);
  }

  @GetMapping("database/allTables")
  public List<String> getAllTables() {
    return tableInfoService.getAllTables();
  }


}
