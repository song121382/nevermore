package com.defence.nevermore.config;

import com.defence.nevermore.config.security.JwtApiIntercptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ProjectName: nevermore
 * @Package: com.defence.nevermore.config
 * @ClassName: WebMvcConfig
 * @Description: java类作用描述
 * @Author: songxiangfu
 * @CreateDate: 2019/4/10 14:23
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/10 14:23
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    //关键，将拦截器作为bean写入配置中,如果用@comment注解在jwtApiIntercptro类上的话，会出现类中@Autowired的类为null
    @Bean
    public JwtApiIntercptor jwtApiIntercptor(){
        return new JwtApiIntercptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加自定义jwt api鉴权
        registry.addInterceptor(jwtApiIntercptor()).addPathPatterns("/**")
        .excludePathPatterns("/login","/register");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //swagger
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
    }
}
