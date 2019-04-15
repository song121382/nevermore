package com.defence.nevermore.config.dataSource;

import java.io.Serializable;

/**
 * mybatis数据库映射关系
 * @author forenns
 *
 */
public class MybatisAliasesMapper implements Serializable{

	private static final long serialVersionUID = -8412246675107920556L;
	
	private String typeAliasesPackage;
	private String mapperLocations;

	public String getTypeAliasesPackage() {
		return typeAliasesPackage;
	}

	public void setTypeAliasesPackage(String typeAliasesPackage) {
		this.typeAliasesPackage = typeAliasesPackage;
	}

	public String getMapperLocations() {
		return mapperLocations;
	}

	public void setMapperLocations(String mapperLocations) {
		this.mapperLocations = mapperLocations;
	}
}
