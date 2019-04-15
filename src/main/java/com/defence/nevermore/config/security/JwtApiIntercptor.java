package com.defence.nevermore.config.security;

import com.alibaba.fastjson.JSON;
import com.defence.nevermore.domain.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * @ProjectName: nevermore
 * @Package: com.defence.nevermore.config.security
 * @ClassName: JwtApiIntercptor
 * @Description: java类作用描述
 * @Author: songxiangfu
 * @CreateDate: 2019/4/12 17:31
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/12 17:31
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * jwt api鉴权
 *
 */
//@Component
public class JwtApiIntercptor implements HandlerInterceptor {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtConfig jwtConfig;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取httpresonse header 中 Authorization名称的jwt
        String authHeader = request.getHeader(jwtConfig.getHeader());
        String tokenHead =jwtConfig.getTokenHead() ;
//        解析jwt
        if (authHeader != null && authHeader.startsWith(tokenHead)) {
            String authToken = authHeader.substring(tokenHead.length());
            //取出jwt中的用户信息
            String username = jwtTokenUtil.getUsernameFromToken(authToken);
            if (username != null /*&& SecurityContextHolder.getContext().getAuthentication() == null*/) {
                //登录验证，返回userdetail
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                //判断令牌有效性
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    //springSecurity 设置authentication
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    return true;
                }
            }
        }
        try {
            PrintWriter printWriter = null;
            OutputStreamWriter outputStreamWriter = null;
            response.setContentType("application/json; charset=utf-8");
            OutputStream ops = response.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(ops,"utf-8");
            printWriter = new PrintWriter(outputStreamWriter, true);
            Response r = Response.error("token 不合法");
            printWriter.write(JSON.toJSONString(r));
            printWriter.flush();
            printWriter.close();
            ops.close();
        } catch (Exception e) {

        }


        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
