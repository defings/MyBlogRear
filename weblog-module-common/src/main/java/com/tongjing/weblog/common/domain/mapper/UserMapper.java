package com.tongjing.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mysql.cj.util.StringUtils;
import com.tongjing.weblog.common.domain.dos.UserDO;
import com.tongjing.weblog.common.domain.vo.FindUserAllResp;
import com.tongjing.weblog.common.domain.vo.FindUserInfoRspVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/7 17:03]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/7 17:03]
 * @updateRemark : [说明本次修改内容]
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
    default UserDO findByUsername(String username) {
        LambdaQueryWrapper<UserDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserDO::getUsername, username)
                .eq(UserDO::getIsDeleted, 1);
        return selectOne(wrapper);
    }

    default int updatePasswordByUsername(String username, String password) {
        LambdaUpdateWrapper<UserDO> wrapper = new LambdaUpdateWrapper<>();
        // 设置要更新的字段
        wrapper.set(UserDO::getPassword, password);
        wrapper.set(UserDO::getUpdateTime, LocalDateTime.now());
        // 更新条件
        wrapper.eq(UserDO::getUsername, username);

        return update(null, wrapper);
    }

    @Select("SELECT t_user.username, t_user.avatar_path FROM t_user WHERE t_user.username = #{userName}")
    public FindUserInfoRspVO findUserInfoByUserName(@Param("userName") String userName);

    @Select("SELECT t_user.username, t_user.id, t_user.avatar_path, t_user.create_time, t_user.is_deleted, t_user_role.role FROM t_user JOIN t_user_role ON t_user.username = t_user_role.username")
    public List<FindUserAllResp> findUserAll();

    default int updateUserInfoById(Long id, String username, String avatarPath) {
        LambdaUpdateWrapper<UserDO> wrapper = new LambdaUpdateWrapper<>();
        if (!StringUtils.isNullOrEmpty(username))wrapper.set(UserDO::getUsername, username);
        if (!StringUtils.isNullOrEmpty(avatarPath))wrapper.set(UserDO::getAvatarPath, avatarPath);
        wrapper.set(UserDO::getUpdateTime, LocalDateTime.now());
        // 更新条件
        wrapper.eq(UserDO::getId, id);
        return update(null, wrapper);
    }

    default int upDataUserState(Long id, int state){
        LambdaUpdateWrapper<UserDO> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(UserDO::getIsDeleted, state);
        wrapper.eq(UserDO::getId, id);
        return update(null, wrapper);
    }
}
