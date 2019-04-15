package com.defence.nevermore.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: nevermore
 * @Package: com.defence.nevermore.config.security
 * @ClassName: JwtAuthenticationTokenFilter
 * @Description: java类作用描述
 * @Author: songxiangfu
 * @CreateDate: 2019/4/10 14:59
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/10 14:59
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * Token过滤器实现
 */
//@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtConfig jwtConfig;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
//        //获取httpresonse header 中 Authorization名称的jwt
//        String authHeader = request.getHeader(jwtConfig.getHeader());
//        String tokenHead =jwtConfig.getTokenHead() ;
//        //解析jwt
//        if (authHeader != null && authHeader.startsWith(tokenHead)) {
//            String authToken = authHeader.substring(tokenHead.length());
//            //取出jwt中的用户信息
//            String username = jwtTokenUtil.getUsernameFromToken(authToken);
//            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                //登录验证，返回userdetail
//                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//                //判断令牌有效性
//                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
//                    //springSecurity 设置authentication
//                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                }
//            }
//        }
        chain.doFilter(request, response);
    }



}
