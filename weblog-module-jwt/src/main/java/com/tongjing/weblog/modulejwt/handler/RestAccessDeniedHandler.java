package com.tongjing.weblog.modulejwt.handler;

import com.tongjing.weblog.common.enums.ResponseCodeEnum;
import com.tongjing.weblog.common.utils.Response;
import com.tongjing.weblog.modulejwt.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/7 19:12]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/7 19:12]
 * @updateRemark : [说明本次修改内容]
 */
@Slf4j
@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.warn("登录成功访问受保护的资源，但是权限不够: {}", accessDeniedException.getMessage());
        // 预留，后面引入多角色时会用到
        ResultUtil.fail(response, Response.fail(ResponseCodeEnum.FORBIDDEN));
    }
}