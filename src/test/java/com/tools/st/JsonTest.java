package com.tools.st;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tools.st.utl.LDTJacksonModule;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.tools.st.utl.JsonUtl;
import com.tools.st.vo.User;

@Slf4j
public class JsonTest {

  @Test
  public void testFile() throws IOException {
    //File dest = new ClassPathResource("/data/columns.json").getFile();
    File dest = Files.createFile(Paths.get("E:\\wgf\\java\\source\\st\\st\\src\\test\\resources\\data\\test2.json")).toFile();
    JsonUtl.toFile(dest, new User(99, "xxx"));
  }

  @Test
  public void testDate() throws IOException {
//        LocalDateTimeSerializer.INSTANCE.
    final ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    //后加入的module放在前面，优先
    mapper.registerModule(new LDTJacksonModule());
    mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    LombokTest.TestObj obj = LombokTest.TestObj.builder().dd(new Date()).ldt(LocalDateTime.now()).build();
//        TestObj obj = new TestObj();
    log.info(mapper.writeValueAsString(obj));
//        log.info(mapper.writeValueAsString(LocalDateTime.now()));
//        log.info(mapper.writeValueAsString(new Date()));
  }
}
