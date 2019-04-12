package com.defence.nevermore.controller;

import com.defence.nevermore.domain.Response;
import com.defence.nevermore.domain.User;
import com.defence.nevermore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: nevermore
 * @Package: com.defence.nevermore.controller
 * @ClassName: UserController
 * @Description: java类作用描述
 * @Author: songxiangfu
 * @CreateDate: 2019/4/10 15:11
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/10 15:11
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 *
 * 用户管理Controller
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/user"/*, produces = "text/html;charset=UTF-8"*/)
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 操作结果
     * @throws AuthenticationException 错误信息
     */
    @PostMapping(value = "/login", params = {"username", "password"})
    @ResponseBody
    public Response getToken(String username, String password){
        try {
            Map<String, Object> resultMap = new HashMap<>();
            String jwt = userService.login(username, password);
            resultMap.put("jwt", jwt);
            return Response.success(resultMap);
        } catch (UsernameNotFoundException e) {
            return Response.error("error", e.getMessage());
        } catch (AuthenticationException e) {
            return Response.error("error", e.getMessage());
        }
    }

    /**
     * 用户注册
     *
     * @param user          用户信息
     * @return 操作结果
     * @throws AuthenticationException 错误信息
     */
    @PostMapping(value = "/register")
    public String register(User user) throws AuthenticationException {
        return userService.register(user);
    }

    /**
     * 刷新密钥
     *
     * @param authorization 原密钥
     * @return 新密钥
     * @throws AuthenticationException 错误信息
     */
    @GetMapping(value = "/refreshToken")
    public String refreshToken(@RequestHeader String authorization) throws AuthenticationException {
        return userService.refreshToken(authorization);
    }

    /**
     * 获取信息
     * @param username
     * @return
     */
//    @PreAuthorize("#username == authentication.name")
    @GetMapping(value = "/getInfo")
    @ResponseBody
    public Response getInfo(String username) {
        return Response.success(userService.getUser(username).getUsername());
    }

}

