package com.defence.nevermore.service.impl;

import com.defence.nevermore.config.security.JwtTokenUtil;
import com.defence.nevermore.domain.ds1.User;
import com.defence.nevermore.mapper.UserMapper;
import com.defence.nevermore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: nevermore
 * @Package: com.defence.nevermore.service.impl
 * @ClassName: UserServiceImpl
 * @Description: java类作用描述
 * @Author: songxiangfu
 * @CreateDate: 2019/4/10 15:09
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/10 15:09
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil, UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userMapper = userMapper;
    }

    @Override
    public String login(String username, String password)throws AuthenticationException {
        //springSecurity 设置authentication
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //通过username登录验证，返回userdetail
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtTokenUtil.generateToken(userDetails);//生成jwt,将userdetails保存进去
    }


    @Override
    public String register(User user) {
        String username = user.getUsername();
        if (userMapper.selectByPrimaryKey(username) != null) {
            return "用户已存在";
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = user.getPassword();
        user.setPassword(encoder.encode(rawPassword));
//        List<String> roles = new ArrayList<>();
//        roles.add("ROLE_USER");
//        user.setRoles(roles);
        userMapper.insert(user);
        return "success";
    }

    @Override
    public String refreshToken(String oldToken) {
        String token = oldToken.substring("Bearer ".length());
        if (!jwtTokenUtil.isTokenExpired(token)) {
            return jwtTokenUtil.refreshToken(token);
        }
        return "error";
    }

    @Override
    public User getUser(String username) {
        return userMapper.selectByPrimaryKey(username);
    }


}
