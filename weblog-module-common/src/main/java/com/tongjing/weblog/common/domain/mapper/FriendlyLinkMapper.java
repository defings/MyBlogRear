package com.tongjing.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tongjing.weblog.common.domain.dos.FriendlyLinkDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/5/16 10:41]
 * @updateUser : [TongJing]
 * @updateTime : [2024/5/16 10:41]
 * @updateRemark : [说明本次修改内容]
 */
@Mapper
public interface FriendlyLinkMapper extends BaseMapper<FriendlyLinkDo> {
    default List<FriendlyLinkDo> findAll(){
        LambdaQueryWrapper<FriendlyLinkDo> friendlyLinkDoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        return selectList(friendlyLinkDoLambdaQueryWrapper);
    }
}
