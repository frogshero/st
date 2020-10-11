package com.tools.st;

import org.junit.jupiter.api.Test;
import org.stringtemplate.v4.ST;

public class STTest {
  
  @Test
  public void testST() {
    ST hello = new ST("Hello, <name>");
    hello.add("name", "World");
    System.out.println(hello.render());
  }
}
