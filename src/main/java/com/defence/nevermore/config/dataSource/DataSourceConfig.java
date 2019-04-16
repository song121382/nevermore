package com.defence.nevermore.config.dataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.datasource")
@PropertySource("classpath:application.yml")
public class DataSourceConfig {

	private DataSource ds1;
	private DataSource ds2;

	public DataSource getDs1() {
		return ds1;
	}
	public void setDs1(DataSource ds1) {
		this.ds1 = ds1;
	}
	public DataSource getDs2() {
		return ds2;
	}
	public void setDs2(DataSource ds2) {
		this.ds2 = ds2;
	}
	
}
