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
