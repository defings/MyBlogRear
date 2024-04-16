package com.tongjing.weblog.common.domain.mapper;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/14 14:47]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/14 14:47]
 * @updateRemark : [说明本次修改内容]
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tongjing.weblog.common.domain.dos.StatisticsArticlePVDO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface StatisticsArticlePVMapper extends BaseMapper<StatisticsArticlePVDO> {
    /**
     * 对指定日期的文章 PV 访问量进行 +1
     * @param date
     * @return
     */
    default int increasePVCount(LocalDate date) {
        return update(null, Wrappers.<StatisticsArticlePVDO>lambdaUpdate()
                .setSql("pv_count = pv_count + 1")
                .eq(StatisticsArticlePVDO::getPvDate, date));
    }
    /**
     * 查询最近一周的文章 PV 访问量记录
     * @return
     */
    default List<StatisticsArticlePVDO> selectLatestWeekRecords() {
        return selectList(Wrappers.<StatisticsArticlePVDO>lambdaQuery()
                .le(StatisticsArticlePVDO::getPvDate, LocalDate.now().plusDays(1)) // 小于明天
                .orderByDesc(StatisticsArticlePVDO::getPvDate)
                .last("limit 7")); // 仅查询七条
    }
}