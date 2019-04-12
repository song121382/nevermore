package com.defence.nevermore.config;

import com.defence.nevermore.config.security.JwtApiIntercptor;
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
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加自定义jwt api鉴权
        registry.addInterceptor(new JwtApiIntercptor()).addPathPatterns("/user/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //swagger
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
    }
}
