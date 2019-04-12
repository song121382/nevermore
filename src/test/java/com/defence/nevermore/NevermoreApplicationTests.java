package com.defence.nevermore;

import com.defence.nevermore.config.security.JwtConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NevermoreApplication.class)
public class NevermoreApplicationTests {

    @Autowired
    private JwtConfig jwtConfig;

    @Test
    public void contextLoads() {
        System.out.println(jwtConfig.getHeader());

    }

}
