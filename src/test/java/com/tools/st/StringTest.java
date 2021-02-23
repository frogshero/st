package com.tools.st;

import com.tools.st.utl.StrUtl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class StringTest {

  @Test
  public void testStr() {
    System.out.println("TABLE_CATALOG".toLowerCase().replaceAll("\\_[a-z]", "$0"));
  }

  @Test
  public void testUtil() {
    log.info(StrUtl.getShortName("TABLE_CATALOG"));
    log.info(StrUtl.getShortName("aa_bb_cc"));
    log.info(StrUtl.getObjName("TABLE_CATALOG"));
    log.info(StrUtl.getObjName("aa_bb_cc"));
  }

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
