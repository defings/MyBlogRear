package com.tongjing.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tongjing.weblog.common.domain.dos.CategoryDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/8 15:00]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/8 15:00]
 * @updateRemark : [说明本次修改内容]
 */
@Mapper
public interface CategoryMapper extends BaseMapper<CategoryDO> {
    default CategoryDO selectByName(String categoryName){
        LambdaQueryWrapper<CategoryDO> wrapper =  new LambdaQueryWrapper<>();
        wrapper.eq(CategoryDO::getName, categoryName);
        return selectOne(wrapper);
    }
}
