package com.tongjing.weblog.web.service;

import com.tongjing.weblog.common.utils.Response;
import com.tongjing.weblog.web.model.vo.SearchArticlePageListReqVO;

/**
 * @author : [TongJing]--------GitHub：<a href="https://github.com/defings">...</a>
 * @version : [v1.0]
 * @description : TODO
 * @createTime : [2024/4/14 16:07]
 * @updateUser : [TongJing]
 * @updateTime : [2024/4/14 16:07]
 * @updateRemark : [说明本次修改内容]
 */
public interface SearchService {

    /**
     * 关键词分页搜索
     * @param searchArticlePageListReqVO
     * @return
     */
    Response searchArticlePageList(SearchArticlePageListReqVO searchArticlePageListReqVO);
}