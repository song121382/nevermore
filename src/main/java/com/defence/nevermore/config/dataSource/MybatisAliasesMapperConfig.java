package com.defence.nevermore.config.dataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mybatis")
public class MybatisAliasesMapperConfig {
    
	private MybatisAliasesMapper ds1;
	private MybatisAliasesMapper ds2;

	public MybatisAliasesMapper getDs1() {
		return ds1;
	}
	public void setDs1(MybatisAliasesMapper ds1) {
		this.ds1 = ds1;
	}

	public MybatisAliasesMapper getDs2() {
		return ds2;
	}

	public void setDs2(MybatisAliasesMapper ds2) {
		this.ds2 = ds2;
	}
}
