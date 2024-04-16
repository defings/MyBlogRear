package com.tongjing.weblog.web.service;

import com.tongjing.weblog.common.utils.Response;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/14 15:17]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/14 15:17]
 * @updateRemark : [说明本次修改内容]
 */
public interface StatisticsService {
    /**
     * 获取文章总数、分类总数、标签总数、总访问量统计信息
     * @return
     */
    Response findInfo();
}

