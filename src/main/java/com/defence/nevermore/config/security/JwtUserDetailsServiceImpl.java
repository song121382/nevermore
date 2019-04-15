package com.defence.nevermore.config.security;


import com.defence.nevermore.domain.ds1.User;
import com.defence.nevermore.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: nevermore
 * @Package: com.defence.nevermore.service
 * @ClassName: JwtUserDetailsServiceImpl
 * @Description: java类作用描述
 * @Author: songxiangfu
 * @CreateDate: 2019/4/10 14:41
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/10 14:41
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * 用户验证方法
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询数据库，进行登录验证
        User user = userMapper.selectByPrimaryKey(username);
        if (user == null) {
            //手动抛出异常
            throw new UsernameNotFoundException(String.format("请检查用户名'%s'.", username));
        } else {
            //构建userDetail信息给springSecurity，让他做登录验证
            return new JwtUser(user.getUsername(), user.getPassword());
        }
    }

}
