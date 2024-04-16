package com.tongjing.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tongjing.weblog.common.domain.dos.CategoryDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
    /**
     * 查询时指定数量
     * @param limit
     * @return
     */
    default List<CategoryDO> selectByLimit(Long limit) {
        return selectList(Wrappers.<CategoryDO>lambdaQuery()
                .orderByDesc(CategoryDO::getArticlesTotal) // 根据文章总数降序
                .last(String.format("LIMIT %d", limit))); // 查询指定数量
    }
}
