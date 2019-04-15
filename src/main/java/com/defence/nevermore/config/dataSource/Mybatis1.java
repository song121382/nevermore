package com.defence.nevermore.config.dataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;


@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.defence.nevermore.mapper.ds1", sqlSessionTemplateRef = "ds1SqlSessionTemplate")
public class Mybatis1 {
	
	private String mybatisConfig="mybatis-config.xml";
	@Autowired
    private DataSourceConfig dataSourceConfig;
	@Autowired
	private MybatisAliasesMapperConfig mybatisAliasesMapperConfig;
    
    /**
     * druid数据源状态监控.
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //白名单：
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        //servletRegistrationBean.addInitParameter("deny", "192.168.1.73");
        //登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "sxf12345");
        //是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    /**
     * druid过滤器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    @Bean(name = "ds1DataSource")
    @Primary
    public DataSource ds1DataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setName(dataSourceConfig.getDs1().getDbName());
        druidDataSource.setUrl(dataSourceConfig.getDs1().getJdbcUrl());
        druidDataSource.setUsername(dataSourceConfig.getDs1().getUsername());       
        druidDataSource.setPassword(dataSourceConfig.getDs1().getPassword());
        druidDataSource.setDriverClassName(dataSourceConfig.getDs1().getDriverClassName());
        druidDataSource.setFilters(dataSourceConfig.getDs1().getFilters());
        druidDataSource.setMaxActive(dataSourceConfig.getDs1().getMaxActive());
        druidDataSource.setInitialSize(dataSourceConfig.getDs1().getInitialSize());
        druidDataSource.setMaxWait(dataSourceConfig.getDs1().getMaxWait());
        druidDataSource.setMinIdle(dataSourceConfig.getDs1().getMinIdle());
        druidDataSource.setTimeBetweenEvictionRunsMillis(dataSourceConfig.getDs1().getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(dataSourceConfig.getDs1().getMinEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(dataSourceConfig.getDs1().getValidationQuery());
        druidDataSource.setTestWhileIdle(dataSourceConfig.getDs1().isTestWhileIdle());
        druidDataSource.setTestOnBorrow(dataSourceConfig.getDs1().isTestOnBorrow());
        druidDataSource.setTestOnReturn(dataSourceConfig.getDs1().isTestOnReturn());
        druidDataSource.setPoolPreparedStatements(dataSourceConfig.getDs1().isPoolPreparedStatements());
        druidDataSource.setMaxOpenPreparedStatements(dataSourceConfig.getDs1().getMaxOpenPreparedStatements());
        return druidDataSource;
    }

    @Bean(name = "ds1TransactionManager")
    public DataSourceTransactionManager ds1TransactionManager(@Qualifier("ds1DataSource") DataSource ds1DataSource) throws SQLException {
        return new DataSourceTransactionManager(ds1DataSource());
    }

    @Bean(name = "ds1SessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("ds1DataSource") DataSource ds1DataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(ds1DataSource);
        sessionFactory.setTypeAliasesPackage(mybatisAliasesMapperConfig.getDs1().getTypeAliasesPackage());
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(mybatisAliasesMapperConfig.getDs1().getMapperLocations()));
        sessionFactory.setConfigLocation(new ClassPathResource(mybatisConfig));
        return sessionFactory.getObject();
    }

    @Bean(name = "ds1SqlSessionTemplate")
    public SqlSessionTemplate ds1SqlSessionTemplate(@Qualifier("ds1SessionFactory") SqlSessionFactory masterSqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(masterSqlSessionFactory);
    }

}
