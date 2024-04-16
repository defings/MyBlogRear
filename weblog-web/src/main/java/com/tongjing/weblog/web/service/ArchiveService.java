package com.tongjing.weblog.web.service;

import com.tongjing.weblog.common.utils.Response;
import com.tongjing.weblog.web.model.vo.FindArchiveArticlePageListReqVO;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/13 17:50]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/13 17:50]
 * @updateRemark : [说明本次修改内容]
 */
public interface ArchiveService {
    /**
     * 获取文章归档分页数据
     * @param findArchiveArticlePageListReqVO
     * @return
     */
    Response findArchivePageList(FindArchiveArticlePageListReqVO findArchiveArticlePageListReqVO);
}