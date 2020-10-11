package com.tools.st;

import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

public class StringTest {

  @Test
  public void testStr() {
    System.out.println(StringUtils.capitalize("TABLE_CATALOG".toLowerCase().replaceAll("_?", "")));
  }
}
