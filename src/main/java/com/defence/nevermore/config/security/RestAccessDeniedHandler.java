package com.defence.nevermore.config.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName: nevermore
 * @Package: com.defence.nevermore.config.security
 * @ClassName: RestAccessDeniedHandler
 * @Description: java类作用描述
 * @Author: songxiangfu
 * @CreateDate: 2019/4/10 15:02
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/10 15:02
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 *
 * 自定403返回值
 */
@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setStatus(403);
    }

}
