package com.tongjing.weblog.admin.service;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/14 18:08]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/14 18:08]
 * @updateRemark : [说明本次修改内容]
 */
public interface AdminStatisticsService {

    /**
     * 统计各分类下文章总数
     */
    void statisticsCategoryArticleTotal();

    /**
     * 统计各标签下文章总数
     */
    void statisticsTagArticleTotal();

}