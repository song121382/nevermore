package com.defence.nevermore.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName: nevermore
 * @Package: com.defence.nevermore.config
 * @ClassName: MybatisConfig
 * @Description: java类作用描述
 * @Author: songxiangfu
 * @CreateDate: 2019/4/10 15:35
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/10 15:35
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Configuration
@MapperScan("com.defence.nevermore.mapper")
public class MybatisConfig {

}
