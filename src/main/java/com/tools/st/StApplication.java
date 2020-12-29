package com.tools.st;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@MapperScan(basePackages = {"com.tools.st.**.mapper", "com.tools.st.**.dao"})
@EnableTransactionManagement
@SpringBootApplication
@RestController
@Slf4j
@Data
public class StApplication {

	@Value("${test.jvm.prop}")
	private String testProp;

	public static void main(String[] args) {
		SpringApplication.run(StApplication.class, args);
	}

	//--test.jvm.prop=aaaaaaa -Dtest.jvm.prop=999999
	@GetMapping("/test")
	public String test() {
		log.info(testProp);
		return testProp;
	}

}
