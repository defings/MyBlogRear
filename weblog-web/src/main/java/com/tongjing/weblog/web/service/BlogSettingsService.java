package com.tongjing.weblog.web.service;

import com.tongjing.weblog.common.utils.Response;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/12 10:38]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/12 10:38]
 * @updateRemark : [说明本次修改内容]
 */
public interface BlogSettingsService {
    /**
     * 获取博客设置信息
     * @return
     */
    Response findDetail();
}
