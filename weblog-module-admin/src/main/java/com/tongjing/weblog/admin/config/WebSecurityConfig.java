package com.tongjing.weblog.admin.config;

import com.tongjing.weblog.modulejwt.config.JwtAuthenticationSecurityConfig;
import com.tongjing.weblog.modulejwt.filter.TokenAuthenticationFilter;
import com.tongjing.weblog.modulejwt.handler.RestAccessDeniedHandler;
import com.tongjing.weblog.modulejwt.handler.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO Spring Security 配置类
 * @createTime : [2024/4/7 18:03]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/7 18:03]
 * @updateRemark : [说明本次修改内容]
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private JwtAuthenticationSecurityConfig jwtAuthenticationSecurityConfig;

    private RestAuthenticationEntryPoint authEntryPoint;

    private RestAccessDeniedHandler deniedHandler;

    private RestAccessDeniedHandler restAccessDeniedHandler;

    @Autowired
    public void setJwtAuthenticationSecurityConfig(JwtAuthenticationSecurityConfig jwtAuthenticationSecurityConfig) {
        this.jwtAuthenticationSecurityConfig = jwtAuthenticationSecurityConfig;
    }
    @Autowired
    public void setAuthEntryPoint(RestAuthenticationEntryPoint authEntryPoint) {
        this.authEntryPoint = authEntryPoint;
    }
    @Autowired
    public void setDeniedHandler(RestAccessDeniedHandler deniedHandler) {
        this.deniedHandler = deniedHandler;
    }
    @Autowired
    public void setRestAccessDeniedHandler(RestAccessDeniedHandler restAccessDeniedHandler) {
        this.restAccessDeniedHandler = restAccessDeniedHandler;
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(). // 禁用 csrf
                formLogin().disable() // 禁用表单登录
                .apply(jwtAuthenticationSecurityConfig) // 设置用户登录认证相关配置
                .and()
                .authorizeHttpRequests()
                .mvcMatchers("/admin/**").authenticated() // 认证所有以 /admin 为前缀的 URL 资源
                .anyRequest().permitAll() // 其他都需要放行，无需认证
                .and()
                .httpBasic().authenticationEntryPoint(authEntryPoint)// 处理用户未登录访问受保护的资源的情况
                .and()
                .exceptionHandling().accessDeniedHandler(deniedHandler) // 处理登录成功后访问受保护的资源，但是权限不够的情况
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 前后端分离，无需创建会话
                .and()
                .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class) // 将 Token 校验过滤器添加到用户认证过滤器之前
        ;
    }

    /**
     * Token 校验过滤器
     * @return
     */
    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }
}
