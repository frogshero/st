package com.tools.st.utl;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtl {

  private static ObjectMapper om = new ObjectMapper();
  
  public static String toJson(Object o) throws JsonProcessingException {
    return om.writeValueAsString(o);
  }
  
  public static void toFile(File f, Object o) throws IOException {
    om.writeValue(f, o);
  }
}
