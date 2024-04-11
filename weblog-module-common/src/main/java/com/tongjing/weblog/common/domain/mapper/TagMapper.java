package com.tongjing.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tongjing.weblog.common.domain.dos.TagDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/8 19:35]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/8 19:35]
 * @updateRemark : [说明本次修改内容]
 */
@Mapper
public interface TagMapper extends BaseMapper<TagDo> {
    default TagDo selectByName(String name){
        LambdaQueryWrapper<TagDo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TagDo::getName, name);
        return selectOne(wrapper);
    }
    /**
     * 根据标签模糊查询
     * @param key
     * @return
     */
    default List<TagDo> selectByKey(String key) {
        LambdaQueryWrapper<TagDo> wrapper = new LambdaQueryWrapper<>();

        // 构造模糊查询的条件
        wrapper.like(TagDo::getName, key).orderByDesc(TagDo::getCreateTime);

        return selectList(wrapper);
    }
}
