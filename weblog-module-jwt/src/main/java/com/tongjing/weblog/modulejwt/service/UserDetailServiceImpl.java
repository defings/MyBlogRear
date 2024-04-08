package com.tongjing.weblog.modulejwt.service;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.tongjing.weblog.common.domain.dos.UserDO;
import com.tongjing.weblog.common.domain.dos.UserRoleDO;
import com.tongjing.weblog.common.domain.mapper.UserMapper;
import com.tongjing.weblog.common.domain.mapper.UserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    private UserMapper userMapper;
    private UserRoleMapper userRoleMapper;
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    @Autowired
    public void setUserRoleMapper(UserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中查询
        UserDO userDO = userMapper.findByUsername(username);
        // 判断用户是否存在
        if (Objects.isNull(userDO)) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        List<UserRoleDO> userRoleDOList = userRoleMapper.selectByUsername(userDO.getUsername());
        String[] roleArr = null;
        // 转数组
        if (!CollectionUtils.isEmpty(userRoleDOList)) {
            roleArr = userRoleDOList.stream().map(UserRoleDO::getRole).toArray(String[]::new);
        }

        // authorities 用于指定角色，这里写死为 ADMIN 管理员
        return User.withUsername(userDO.getUsername())
                .password(userDO.getPassword())
                .authorities(roleArr)
                .build();
    }
}
