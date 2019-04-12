package com.defence.nevermore.service;

import com.defence.nevermore.domain.User;

/**
 * @ProjectName: nevermore
 * @Package: com.defence.nevermore.service
 * @ClassName: UserService
 * @Description: java类作用描述
 * @Author: songxiangfu
 * @CreateDate: 2019/4/10 15:08
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/10 15:08
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface UserService {
    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 操作结果
     */
    String login(String username, String password);

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return 操作结果
     */
    String register(User user);

    /**
     * 刷新密钥
     *
     * @param oldToken 原密钥
     * @return 新密钥
     */
    String refreshToken(String oldToken);

    /**
     * 查取信息
     */
    User getUser(String username);
}
