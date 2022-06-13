package com.db.imas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author: noname
 * @CreateDate: 2022/5/31 15:26
 * @Version: 1.0.0
 * @Company: sakuraoka
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 放行所有请求
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll();
    }

}
