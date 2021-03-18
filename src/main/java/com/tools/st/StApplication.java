package com.tools.st;

import com.tools.st.service.MyExecutor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.Name;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

@MapperScan(basePackages = {"com.tools.st.**.mapper", "com.tools.st.**.dao"})
@EnableTransactionManagement
@SpringBootApplication
@RestController
@Slf4j
@Data
@EnableScheduling
public class StApplication {

	@Value("${test.jvm.prop:${test.jvm.propDef}}")
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

	@Bean
	public MyExecutor testExecutor() {
		//ThreadPoolTaskScheduler
		MyExecutor testExecutor = new MyExecutor(1, (c)->
			new Thread(c,"TESTSC_" + RandomStringUtils.randomNumeric(4)));
		return testExecutor;
	}



}
