package com.defence.nevermore.config.dataSource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;


@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.defence.nevermore.mapper.ds2", sqlSessionTemplateRef = "ds2SqlSessionTemplate")
public class Mybatis2 {

	private String mybatisConfig="mybatis-config.xml";
	@Autowired
    private DataSourceConfig dataSourceConfig;
	@Autowired
	private MybatisAliasesMapperConfig mybatisAliasesMapperConfig;


    @Bean(name = "ds2Datasource")
    public DataSource ds2Datasource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setName(dataSourceConfig.getDs2().getDbName());
        druidDataSource.setUrl(dataSourceConfig.getDs2().getJdbcUrl());
        druidDataSource.setUsername(dataSourceConfig.getDs2().getUsername());
        druidDataSource.setPassword(dataSourceConfig.getDs2().getPassword());
        druidDataSource.setDriverClassName(dataSourceConfig.getDs2().getDriverClassName());
        druidDataSource.setFilters(dataSourceConfig.getDs2().getFilters());
        druidDataSource.setMaxActive(dataSourceConfig.getDs2().getMaxActive());
        druidDataSource.setInitialSize(dataSourceConfig.getDs2().getInitialSize());
        druidDataSource.setMaxWait(dataSourceConfig.getDs2().getMaxWait());
        druidDataSource.setMinIdle(dataSourceConfig.getDs2().getMinIdle());
        druidDataSource.setTimeBetweenEvictionRunsMillis(dataSourceConfig.getDs2().getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(dataSourceConfig.getDs2().getMinEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(dataSourceConfig.getDs2().getValidationQuery());
        druidDataSource.setTestWhileIdle(dataSourceConfig.getDs2().isTestWhileIdle());
        druidDataSource.setTestOnBorrow(dataSourceConfig.getDs2().isTestOnBorrow());
        druidDataSource.setTestOnReturn(dataSourceConfig.getDs2().isTestOnReturn());
        druidDataSource.setPoolPreparedStatements(dataSourceConfig.getDs2().isPoolPreparedStatements());
        druidDataSource.setMaxOpenPreparedStatements(dataSourceConfig.getDs2().getMaxOpenPreparedStatements());
        return druidDataSource;
    }

    @Bean(name = "ds2TransactionManager")
    public DataSourceTransactionManager rdsTransactionManager(@Qualifier("ds2Datasource") DataSource ds2Datasource) throws SQLException {
        return new DataSourceTransactionManager(ds2Datasource());
    }

    @Bean(name = "ds2SessionFactory")
    public SqlSessionFactory secondSqlSessionFactory(@Qualifier("ds2Datasource") DataSource ds2Datasource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(ds2Datasource);
        sessionFactory.setTypeAliasesPackage(mybatisAliasesMapperConfig.getDs2().getTypeAliasesPackage());
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(mybatisAliasesMapperConfig.getDs2().getMapperLocations()));
        sessionFactory.setConfigLocation(new ClassPathResource(mybatisConfig));
        return sessionFactory.getObject();
    }

    @Bean(name = "ds2SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("ds2SessionFactory") SqlSessionFactory ds2SessionFactory) throws Exception {
        return new SqlSessionTemplate(ds2SessionFactory);
    }

}
