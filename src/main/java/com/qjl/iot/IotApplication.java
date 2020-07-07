package com.qjl.iot;


import com.qjl.iot.datasources.DynamicDataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,org.activiti.spring.boot.SecurityAutoConfiguration.class})
@Import({DynamicDataSourceConfig.class})
public class IotApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(IotApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(IotApplication.class);
	}
}
