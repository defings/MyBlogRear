package com.tongjing.weblog.admin.service.impl;

import com.tongjing.weblog.admin.model.vo.UpdateAdminUserPasswordReqVO;
import com.tongjing.weblog.admin.service.AdminUserService;
import com.tongjing.weblog.common.domain.mapper.UserMapper;
import com.tongjing.weblog.common.domain.vo.FindUserInfoRspVO;
import com.tongjing.weblog.common.enums.ResponseCodeEnum;
import com.tongjing.weblog.common.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/8 12:51]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/8 12:51]
 * @updateRemark : [说明本次修改内容]
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 修改密码
     *
     * @param updateAdminUserPasswordReqVO
     * @return
     */
    @Override
    public Response updatePassword(UpdateAdminUserPasswordReqVO updateAdminUserPasswordReqVO) {
        // 拿到用户名、密码
        String username = updateAdminUserPasswordReqVO.getUsername();
        String password = updateAdminUserPasswordReqVO.getPassword();

        // 加密密码
        String encodePassword = passwordEncoder.encode(password);

        // 更新到数据库
        int count = userMapper.updatePasswordByUsername(username, encodePassword);

        return count == 1 ? Response.success() : Response.fail(ResponseCodeEnum.USERNAME_NOT_FOUND);
    }

    @Override
    public Response findUserInfo() {
        // 获取存储在 ThreadLocal 中的用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 拿到用户名
        String username = authentication.getName();
        FindUserInfoRspVO findUserInfoRspVO = userMapper.findUserInfoByUserName(username);
        return Objects.isNull(findUserInfoRspVO) ? Response.fail("没有该用户") : Response.success(findUserInfoRspVO);
    }
}
