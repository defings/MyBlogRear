package com.tongjing.weblog.admin.model.vo;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/14 14:52]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/14 14:52]
 * @updateRemark : [说明本次修改内容]
 */
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "查询仪表盘文章 PV 访问量信息入参 VO")
public class FindDashboardPVStatisticsInfoRspVO {
    /**
     * 日期集合
     */
    private List<String> pvDates;

    /**
     * PV 浏览量集合
     */
    private List<Long> pvCounts;
}