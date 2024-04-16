package com.tongjing.weblog.modulejwt.filter;

import com.tongjing.weblog.modulejwt.utils.JwtTokenHelper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SignatureException;
import java.util.Objects;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/7 18:48]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/7 18:48]
 * @updateRemark : [说明本次修改内容]
 */
@Slf4j
public class TokenAuthenticationFilter  extends OncePerRequestFilter {

    private JwtTokenHelper jwtTokenHelper;


    private UserDetailsService userDetailsService;


    private AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    public void setJwtTokenHelper(JwtTokenHelper jwtTokenHelper) {
        this.jwtTokenHelper = jwtTokenHelper;
    }
    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Autowired
    public void setAuthenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 从请求头中获取 key 为 Authorization 的值
        String token = request.getHeader("token");

        if (StringUtils.isNotBlank(token)) {
            // 判空 Token
            if (StringUtils.isNotBlank(token)) {
                try {
                    // 校验 Token 是否可用, 若解析异常，针对不同异常做出不同的响应参数
                    jwtTokenHelper.validateToken(token);
                } catch (MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
                    authenticationEntryPoint.commence(request, response, new AuthenticationServiceException("Token 不可用"));
                    return;
                } catch (ExpiredJwtException e) {
                    authenticationEntryPoint.commence(request, response, new AuthenticationServiceException("Token 已失效"));
                    return;
                }

                // 从 Token 中解析出用户名
                String username = jwtTokenHelper.getUsernameByToken(token);

                if (StringUtils.isNotBlank(username)
                        && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
                    // 根据用户名获取用户详情信息
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    // 将用户信息存入 authentication，方便后续校验
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                            userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // 将 authentication 存入 ThreadLocal，方便后续获取用户信息
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        // 继续执行写一个过滤器
        filterChain.doFilter(request, response);
    }
}
