package com.tools.st;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.tools.st.utl.JsonUtl;
import com.tools.st.vo.User;

public class JsonTest {

  @Test
  public void testFile() throws IOException {
    //File dest = new ClassPathResource("/data/columns.json").getFile();
    File dest = Files.createFile(Paths.get("E:\\wgf\\java\\source\\st\\st\\src\\test\\resources\\data\\test2.json")).toFile();
    JsonUtl.toFile(dest, new User(99, "xxx"));
  }
}
