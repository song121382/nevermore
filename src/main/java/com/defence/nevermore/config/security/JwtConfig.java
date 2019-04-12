package com.defence.nevermore.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: nevermore
 * @Package: com.defence.nevermore.config.security
 * @ClassName: JwtConfig
 * @Description: java类作用描述
 * @Author: songxiangfu
 * @CreateDate: 2019/4/12 9:47
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/12 9:47
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
    private String header = "Authorization";

    private String secret = "mySecret";

    private String tokenHead = "Bearer ";

    private String expiration = "86400";

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getTokenHead() {
        return tokenHead;
    }

    public void setTokenHead(String tokenHead) {
        this.tokenHead = tokenHead;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }
}
