package com.defence.nevermore;

import com.defence.nevermore.config.dataSource.DataSourceConfig;
import com.defence.nevermore.config.dataSource.MybatisAliasesMapperConfig;
import com.defence.nevermore.config.security.JwtConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NevermoreApplication.class)
public class NevermoreApplicationTests {

    @Autowired
    private JwtConfig jwtConfig;
//    @Autowired
//    private TestMapper testMapper;

    @Autowired
    private DataSourceConfig dataSourceConfig;
    @Autowired
    private MybatisAliasesMapperConfig mybatisAliasesMapperConfig;

    @Test
    public void contextLoads() {
//        System.out.println(jwtConfig.getHeader());
//        List<com.defence.nevermore.domain.Test> list = testMapper.getTT();
        System.out.println(dataSourceConfig.getDs1());


    }

}
