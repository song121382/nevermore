package com.defence.nevermore.config.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName: nevermore
 * @Package: com.defence.nevermore.config.security
 * @ClassName: EntryPointUnauthorizedHandler
 * @Description: java类作用描述
 * @Author: songxiangfu
 * @CreateDate: 2019/4/10 15:01
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/10 15:01
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 *
 * 自定义了身份验证失败的返回值
 */
@Component
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setStatus(401);
    }

}
