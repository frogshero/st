package com.tools.st;

import com.tools.st.utl.StrUtl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

//junit4用@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
@Slf4j
public class StringTest {

  @Test
  public void testJoin() {
    List<String> list = Lists.newArrayList("xx", "aa", "cc");
    log.info(list.stream().collect(Collectors.joining(",")));

    StringJoiner sj = new StringJoiner(":", "[", "]");
    sj.add("George").add("Sally").add("Fred");
    log.info(sj.toString());
  }

  @Test
  public void testFormat() {
    log.info(String.format("%05d", "11"));
    log.info(String.format("%05d", 1));
    log.info(String.format("%05d", 11111));
    log.info(String.format("%05d", 111111));
  }

  @Order(2)
  @Test
  public void testStr() {
    System.out.println("TABLE_CATALOG".toLowerCase().replaceAll("\\_[a-z]", "$0"));
  }

  @Order(1)
  @Test
  public void testUtil() {
    log.info(StrUtl.getShortName("TABLE_CATALOG"));
    log.info(StrUtl.getShortName("aa_bb_cc"));
    log.info(StrUtl.getObjName("TABLE_CATALOG"));
    log.info(StrUtl.getObjName("aa_bb_cc"));
  }

  @Order(3)
  @Test
  public void testReg() {
    Pattern pattern = Pattern.compile("\\_[a-zA-Z]");
    Matcher matcher = pattern.matcher("TABLE_CATALOG");
    StringBuffer sb = new StringBuffer();
    while (matcher.find()) {
      matcher.appendReplacement(sb, matcher.group(0).substring(1).toUpperCase());
    }
    matcher.appendTail(sb);
    log.info(sb.toString());
  }

  @Order(4)
  @Test
  public void testReg3() {
    Pattern pattern = Pattern.compile(".*ID=([a-zA-Z_]*)\\.([a-zA-Z_]*)");
    Matcher matcher = pattern.matcher("入库单ID=mold_warehouse_entry.id");
    matcher.find();
    log.info(matcher.group(1));
    log.info(matcher.group(2));
  }

  @Test
  public void extractStr() {
    String src = "<template>\n" +
            "<el-dialog title=\"新增\" :visible.sync =\"showDlg\" :close-on-click-modal=\"false\" width=\"800px\" :show-close=\"true\" v-dialogDrag @close=\"onDlgClose\" >\n" +
            "    <el-form :model=\"addForm\" label-width=\"140px\" >\n" +
            "        <el-row>\n" +
            "        <el-col :span=\"12\">\n" +
            "            <el-form-item :prop=\"'equipmentId'\" :label=\" '入库单号'\" >\n" +
            "            <el-input size=\"small\" v-model.trim=\"stockInNo\" disabled></el-input>\n" +
            "            </el-form-item>\n" +
            "        </el-col>\n" +
            "        <el-col :span=\"12\">\n" +
            "            <el-form-item :prop=\"'equipmentId'\" :label=\" '开单日期'\" >\n" +
            "                <el-date-picker size=\"small\" disabled v-model=\"beginDate\" type=\"date\" placeholder=\"选择日期\" value-format=\"yyyy-MM-dd HH:mm\" format=\"yyyy-MM-dd\"></el-date-picker>\n" +
            "            </el-form-item>\n" +
            "        </el-col>\n" +
            "        <el-col :span=\"12\">\n" +
            "            <el-form-item :prop=\"'equipmentId'\" :label=\" '入库人'\" >\n" +
            "            <el-input size=\"small\" v-model.trim=\"stockInUserName\"  disabled clearable></el-input>\n" +
            "            </el-form-item>\n" +
            "        </el-col>\n" +
            "        <el-col :span=\"12\">\n" +
            "            <el-form-item :prop=\"'equipmentId'\" :label=\" '入库类型'\" >\n" +
            "            <el-select  size=\"small\" clearable v-model=\"transactionTypeId\" placeholder=\"请选择\">\n" +
            "                <el-option v-for=\"item in transactionTypeList\" :key=\"item.value\" :label=\"item.name\" :value=\"item.value\"></el-option>\n" +
            "            </el-select>\n" +
            "            </el-form-item>\n" +
            "        </el-col>\n" +
            "        <el-col :span=\"12\" justify=\"left\" >\n" +
            "        <el-form-item :prop=\"'equipmentId'\" :label=\" '车辆牌号'\">\n" +
            "            <el-autocomplete class=\"inline-input\" size=\"small\" style=\"width:100%\" v-model=\"numberPlate\" :fetch-suggestions=\"querySearch\"  placeholder=\"请选择\" >\n" +
            "            <el-button  size=\"small\"  slot=\"append\" type=\"primary\" style=\"float:left!important;background:#409EFF;color:#fff\" @click.native=\"numberPlateShow()\">简称</el-button>\n" +
            "            </el-autocomplete>\n" +
            "        </el-form-item>\n" +
            "        </el-col>\n" +
            "        <el-col :span=\"12\">\n" +
            "            <el-form-item :prop=\"'equipmentId'\" :label=\" '供应商'\" >\n" +
            "            <el-input clearable size=\"small\" width=\"100px\" @clear=\"clearAll\" v-model.trim=\"supplierName\" @focus=\"showAssembly()\" >\n" +
            "                <i slot=\"suffix\" class=\"el-input__icon el-icon-search\"></i>\n" +
            "            </el-input>\n" +
            "            </el-form-item>\n" +
            "        </el-col>\n" +
            "        <el-col :span=\"12\">\n" +
            "        <el-form-item :prop=\"'equipmentId'\" :label=\" '物料编码'\" >\n" +
            "            <el-input clearable size=\"small\" disabled width=\"100px\" @clear=\"clearAll\" v-model.trim=\"materielCode\" @focus=\"showMaterial()\" >\n" +
            "            <i slot=\"suffix\" class=\"el-input__icon el-icon-search\" @click=\"showMaterial()\" ></i>\n" +
            "            </el-input>\n" +
            "        </el-form-item>\n" +
            "        </el-col>\n" +
            "        <el-col :span=\"12\">\n" +
            "        <el-form-item :prop=\"'equipmentId'\" :label=\" '物料名称'\" >\n" +
            "            <el-input size=\"small\" v-model.trim=\"materielName\"  disabled></el-input>\n" +
            "        </el-form-item>\n" +
            "        </el-col>\n" +
            "        <el-col :span=\"12\">\n" +
            "        <el-form-item :prop=\"'equipmentId'\" :label=\" '物料批号'\" >\n" +
            "            <el-input size=\"small\" v-model.trim=\"batchNo\" >\n" +
            "            </el-input>\n" +
            "        </el-form-item>\n" +
            "        </el-col>\n" +
            "        <el-col :span=\"12\">\n" +
            "            <el-form-item :prop=\"'equipmentId'\" :label=\" '物料仓库'\" >\n" +
            "            <el-select  size=\"small\" clearable v-model=\"warehouseId\" placeholder=\"请选择\" @change=\"warehouseChange\">\n" +
            "                <el-option v-for=\"item in warehouseList\" :key=\"item.value\" :label=\"item.name\" :value=\"item.value\"></el-option>\n" +
            "            </el-select>\n" +
            "            </el-form-item>\n" +
            "        </el-col>\n" +
            "        <el-col :span=\"12\">\n" +
            "        <el-form-item :prop=\"'equipmentId'\" :label=\" '物料库位'\" >\n" +
            "            <el-input clearable size=\"small\" width=\"100px\" @clear=\"clearAll\" v-model.trim=\"warehousePositionName\" @focus=\"showWarehouse()\" >\n" +
            "            <i slot=\"suffix\" class=\"el-input__icon el-icon-search\" @click=\"showWarehouse()\" ></i>\n" +
            "            </el-input>\n" +
            "        </el-form-item>\n" +
            "        </el-col>\n" +
            "        <el-col :span=\"12\">\n" +
            "        <el-form-item :prop=\"'equipmentId'\" :label=\" '物料毛重'\" >\n" +
            "            <el-input  size=\"small\" clearable v-model=\"roughWeight\" class=\"partName\"  @blur=\"weightChange(1)\">\n" +
            "            <el-button  size=\"small\" slot=\"append\" type=\"primary\" style=\"background:#409EFF;color:#fff\" @click=\"selWeight(1)\">称重</el-button>\n" +
            "            </el-input>\n" +
            "        </el-form-item>\n" +
            "        </el-col>\n" +
            "        <el-col :span=\"12\">\n" +
            "        <el-form-item :prop=\"'equipmentId'\" :label=\" '物料净重'\" >\n" +
            "            <el-input size=\"small\" v-model.trim=\"suttleWeight\" disabled ></el-input>\n" +
            "        </el-form-item>\n" +
            "        </el-col>\n" +
            "        <el-col :span=\"12\">\n" +
            "        <el-form-item :prop=\"'equipmentId'\" :label=\" '物料皮重'\" >\n" +
            "            <el-input size=\"small\" clearable v-model=\"tareWeight\" class=\"partName\"  @blur=\"weightChange(2)\">\n" +
            "            </el-input>\n" +
            "        </el-form-item>\n" +
            "        </el-col>\n" +
            "        <el-col :span=\"12\">\n" +
            "        <el-form-item :prop=\"'equipmentId'\" :label=\" '入库重量'\" >\n" +
            "            <el-input size=\"small\" v-model.trim=\"num\" ></el-input>\n" +
            "        </el-form-item>\n" +
            "        </el-col>\n" +
            "        <el-col :span=\"24\">\n" +
            "        <el-form-item :prop=\"'equipmentId'\" :label=\" '备注'\" >\n" +
            "            <el-input size=\"small\" v-model=\"remarks\" @blur=\"remarks=$event.target.value.trim()\" \n" +
            "            type=\"textarea\" resize=\"none\" :rows=\"3\"></el-input>\n" +
            "        </el-form-item>\n" +
            "        </el-col>\n" +
            "        </el-row>\n" +
            "    </el-form>\n" +
            "    <div slot=\"footer\" class=\"dialog-footer\">\n" +
            "        <el-button size=\"small\" @click.native=\"showDlg = false\">取消</el-button>\n" +
            "        <el-button size=\"small\" type=\"primary\" @click.native=\"submit\">确定</el-button>\n" +
            "    </div>\n" +
            "</el-dialog>\n" +
            "</template>";
    Pattern p = Pattern.compile("v-model(\\.trim)?=\"(\\w*)\"");
    Matcher m = p.matcher(src);
    while (m.find()) {
      System.out.println(m.group(2) + ": null,");
    }
  }

  @Order(5)
  @Test
  public void testReg2() {
    Pattern pattern = Pattern.compile("[A-Z]");
    Matcher matcher = pattern.matcher("tabFasdfXs");
    StringBuffer sb = new StringBuffer();
    while (matcher.find()) {
      matcher.appendReplacement(sb, matcher.group(0).substring(1).toUpperCase());
    }
    matcher.appendTail(sb);
    log.info(sb.toString());
  }
}
