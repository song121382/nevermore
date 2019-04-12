package com.defence.nevermore.domain;

import java.util.List;

/**
 * @ProjectName: nevermore
 * @Package: com.defence.nevermore.domain
 * @ClassName: User
 * @Description: java类作用描述
 * @Author: songxiangfu
 * @CreateDate: 2019/4/10 14:35
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/10 14:35
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class User {
    private String username;
    private String password;
    private List<String> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
