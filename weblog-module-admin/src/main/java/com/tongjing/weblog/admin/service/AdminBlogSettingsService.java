package com.tongjing.weblog.admin.service;

import com.tongjing.weblog.admin.model.vo.UpdateBlogSettingsReqVO;
import com.tongjing.weblog.common.utils.Response;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/9 20:38]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/9 20:38]
 * @updateRemark : [说明本次修改内容]
 */
public interface AdminBlogSettingsService {
    /**
     * 更新博客设置信息
     * @param updateBlogSettingsReqVO
     * @return
     */
    Response updateBlogSettings(UpdateBlogSettingsReqVO updateBlogSettingsReqVO);

    /**
     * 获取博客设置详情
     * @return
     */
    Response findDetail();
}
