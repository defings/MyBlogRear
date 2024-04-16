package com.tongjing.weblog.admin.service;

import com.tongjing.weblog.common.utils.Response;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/14 14:39]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/14 14:39]
 * @updateRemark : [说明本次修改内容]
 */
public interface AdminDashboardService {

    /**
     * 获取仪表盘基础统计信息
     * @return
     */
    Response findDashboardStatistics();
    /**
     * 获取文章发布热点统计信息
     * @return
     */
    Response findDashboardPublishArticleStatistics();
    /**
     * 获取文章最近一周 PV 访问量统计信息
     * @return
     */
    Response findDashboardPVStatistics();
}

