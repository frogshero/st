package com.tools.st.controller;

import java.util.List;

import com.tools.st.entity.SysComPageConfig;
import com.tools.st.entity.SysComTableConfig;
import com.tools.st.service.PageConfigService;
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

  @Autowired
  PageConfigService pageConfigService;

  @ApiOperation("字段信息")
  @GetMapping("database/{schema}/{tabName}")
  public List<ColumnInfo> getColumns(@PathVariable String schema, @PathVariable String tabName) {
    return tableInfoService.getColumnInfos(schema, tabName);
  }

  @GetMapping("database/allTables")
  public List<String> getAllTables() {
    return tableInfoService.getAllTables();
  }

//  @ApiOperation("页面配置")
//  @GetMapping("/config/page/{pageId}")
//  public SysComPageConfig getPage(@PathVariable String pageId) {
//    return pageConfigService.getPage(pageId);
//  }

  @ApiOperation("所有页面配置")
  @GetMapping("/config/pages")
  public List<SysComPageConfig> getPageConfigList() {
    return pageConfigService.getPages();
  }

  @GetMapping("/tableConfig/{tableId}")
  public List<SysComTableConfig> getTableConfig(@PathVariable String tableId) {
    return pageConfigService.getTableConfig(tableId);
  }
}
